package dev.cruding.engine.printer.impl.entite;

import java.util.List;
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
            champ.addDtoImport(f);
        }

        /* *********************************************************************** */
        Champ idChamp = entite.listeChamp.stream().filter(p -> p.isId).findFirst().get();

        f.__("package app.domain.", entite.pkg, ".", entite.lname, ";");
        f.L("");
        f.flushJavaImportBloc();
        f.L("");
        f.L("public record ", entite.uname, "Dto(//");
        f.L________("Long id, //");
        f.L________("Long id", entite.uname, ", //");
        if (!idChamp.lname.equals("libelle")) {
            f.L________("String libelle, //");
        }
        for (int i = 0; i < listeChamp.size(); i++) {
            Champ champ = listeChamp.get(i);
            String end = i == listeChamp.size() - 1 ? " //" : ", //";
            if (champ.isRef || champ.isPere) {
                f.L________(champ.jtype, "Dto ", champ.lname, end);

            } else {
                f.L________(champ.jtype, " ", champ.lname, end);
            }
        }
        f.L(") {");
        f.L("");

        if (!entite.isReferenceData()) {
            f.L____("public static ", entite.uname, "Dto asEntity(", entite.uname, " entity) {");
            f.L________("return entity == null ? null");
            f.L________________(": new ", entite.uname, "Dto(//");
            f.L________________________("entity.getId(), //");
            f.L________________________("entity.getId(), //");
            if (!idChamp.lname.equals("libelle")) {
                f.L________________________("entity.getDisplayString(), //");
            }
            for (int i = 0; i < listeChamp.size(); i++) {
                Champ champ = listeChamp.get(i);
                String end = i == listeChamp.size() - 1 ? " //" : ", //";
                if (champ.isRef || champ.isPere) {
                    f.L________________________(champ.jtype, "Dto.asRef(entity.get", champ.uname, "())", end);
                } else {
                    f.L________________________("entity.get", champ.uname, "()", end);

                }
            }
            f.L________________(");");
            f.L____("}");
            f.L("");
        }
        f.L____("public static ", entite.uname, "Dto asRef(", entite.uname, " entity) {");
        f.L________("return entity == null ? null");
        f.L________________(": new ", entite.uname, "Dto(//");
        f.L________________________("entity.getId(), //");
        f.L________________________("entity.getId(), //");
        if (!idChamp.lname.equals("libelle")) {
            f.L________________________("entity.getDisplayString(), //");
        }
        for (int i = 0; i < listeChamp.size(); i++) {
            Champ champ = listeChamp.get(i);
            String end = i == listeChamp.size() - 1 ? " //" : ", //";
            if (champ.isId) {
                f.L________________________("entity.get", champ.uname, "()", end);
            } else {
                f.L________________________("null", end);
            }
        }
        f.L________________(");");
        f.L____("}");
        f.L("}");

        /* *********************************************************************** */
        String s = f.toString();
        printFile(s, getBasePath() + "/be/src/main/java/app/domain/" + entite.path + "/" + entite.uname + "Dto.java");
    }

}
