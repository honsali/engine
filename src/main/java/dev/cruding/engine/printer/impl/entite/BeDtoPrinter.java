package dev.cruding.engine.printer.impl.entite;

import java.util.List;
import java.util.Optional;
import dev.cruding.engine.champ.Champ;
import dev.cruding.engine.entite.Entite;
import dev.cruding.engine.flow.JavaFlow;
import dev.cruding.engine.printer.Printer;

public class BeDtoPrinter extends Printer {

    public void print(Entite entite) {
        JavaFlow f = new JavaFlow();

        /* *********************************************************************** */
        List<Champ> listeChamp = entite.listeChamp;
        for (Champ champ : listeChamp) {
            if (champ.filtre) {
                continue;
            }
            champ.addDtoImport(f);
            if (champ.requis) {
                f.addJavaImport("jakarta.validation.constraints.NotNull");
            }
        }

        /* *********************************************************************** */
        Optional<Champ> idChamp = entite.listeChamp.stream().filter(p -> p.isId).findFirst();

        f.__("package app.domain.", entite.pkg, ".", entite.lname, ";");
        f.L("");
        f.flushJavaImportBloc();
        f.L("");
        f.L("public record ", entite.uname, "Dto(//");
        f.L________("Long id, //");
        f.L________("Long id", entite.uname, ", //");
        if (idChamp.isPresent() && !idChamp.get().lname.equals("libelle")) {
            f.L________("String libelle, //");
        }
        for (int i = 0; i < listeChamp.size(); i++) {
            Champ champ = listeChamp.get(i);
            if (champ.filtre) {
                continue;
            }
            String end = i == listeChamp.size() - 1 ? " //" : ", //";
            if (champ.isRef || champ.isPere) {
                f.L________(champ.jtype, "Dto ", champ.lname, end);

            } else {
                if (champ.requis) {
                    f.L________("@NotNull");
                }
                f.L________(champ.jtype, " ", champ.lname, end);
            }
        }
        f.L(") {");
        f.L("");

        if (!entite.isReferenceData()) {
            f.L____("public static ", entite.uname, "Dto toDto(", entite.uname, " entity) {");
            f.L________("return entity == null ? null");
            f.L________________(": new ", entite.uname, "Dto(//");
            f.L________________________("entity.getId(), //");
            f.L________________________("entity.getId(), //");
            if (idChamp.isPresent() && !idChamp.get().lname.equals("libelle")) {
                f.L________________________("entity.getDisplayString(), //");
            }
            for (int i = 0; i < listeChamp.size(); i++) {
                Champ champ = listeChamp.get(i);
                if (champ.filtre) {
                    continue;
                }
                String end = i == listeChamp.size() - 1 ? " //" : ", //";
                if (champ.isRef || champ.isPere) {
                    f.L________________________(champ.jtype, "Dto.toDtoAsRef(entity.get", champ.uname, "())", end);
                } else {
                    f.L________________________("entity.get", champ.uname, "()", end);

                }
            }
            f.L________________(");");
            f.L____("}");
            f.L("");
        }
        f.L____("public static ", entite.uname, "Dto toDtoAsRef(", entite.uname, " entity) {");
        f.L________("return entity == null ? null");
        f.L________________(": new ", entite.uname, "Dto(//");
        f.L________________________("entity.getId(), //");
        f.L________________________("entity.getId(), //");
        if (idChamp.isPresent() && !idChamp.get().lname.equals("libelle")) {
            f.L________________________("entity.getDisplayString(), //");
        }
        for (int i = 0; i < listeChamp.size(); i++) {
            Champ champ = listeChamp.get(i);
            if (champ.filtre) {
                continue;
            }
            String end = i == listeChamp.size() - 1 ? " //" : ", //";
            if (champ.isId) {
                f.L________________________("entity.get", champ.uname, "()", end);
            } else {
                f.L________________________("null", end);
            }
        }
        f.L________________(");");
        f.L____("}");

        f.L("");

        if (!entite.isReferenceData()) {
            f.L____("public static ", entite.uname, " toEntity(", entite.uname, "Dto dto) {");
            f.L________("if (dto == null) {");
            f.L____________("return null;");
            f.L________("}");
            f.L("");
            f.L________(entite.uname, " entity = new ", entite.uname, "();");
            for (int i = 0; i < listeChamp.size(); i++) {
                Champ champ = listeChamp.get(i);
                if (champ.filtre) {
                    continue;
                }
                if (champ.isRef || champ.isPere) {
                    f.L________("entity.set", champ.uname, "(", champ.uname, "Dto.toEntityAsRef(dto.", champ.lname, "()));");
                } else {
                    f.L________("entity.set", champ.uname, "(dto.", champ.lname, "());");

                }
            }
            f.L________("return entity;");
            f.L____("}");
            f.L("");
            f.L____("public static ", entite.uname, " toEntity(", entite.uname, "Dto dto, Long id) {");
            f.L________(entite.uname, " entity = toEntity(dto);");
            f.L________("if (entity != null) {");
            f.L____________("entity.setId(id);");
            f.L________("}");
            f.L________("return entity;");
            f.L____("}");
            f.L("");
        }
        f.L____("public static ", entite.uname, " toEntityAsRef(", entite.uname, "Dto dto) {");
        f.L________("if (dto == null || dto.id() == null) {");
        f.L____________("return null;");
        f.L________("}");
        f.L("");
        f.L________(entite.uname, " entity = new ", entite.uname, "();");
        f.L________("entity.setId(dto.id());");
        f.L________("return entity;");
        f.L____("}");

        f.L("}");
        /* *********************************************************************** */
        String s = f.toString();
        printFile(s, getBasePath() + "/be/src/main/java/app/domain/" + entite.path + "/" + entite.uname + "Dto.java");
    }

}
