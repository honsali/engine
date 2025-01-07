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
        List<Champ> fieldList = entite.fieldList;
        for (Champ fld : fieldList) {
            fld.addDtoImport(f);
        }


        /* *********************************************************************** */
        Champ idChamp = entite.fieldList.stream().filter(p -> p.isId).findFirst().get();

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
        for (int i = 0; i < fieldList.size(); i++) {
            Champ fld = fieldList.get(i);
            String end = i == fieldList.size() - 1 ? " //" : ", //";
            if (fld.isRef || fld.isPere) {
                f.L________(fld.jtype, "Dto ", fld.lname, end);

            } else {
                f.L________(fld.jtype, " ", fld.lname, end);
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
                f.L________________________("entity.get", idChamp.uname, "(), //");
            }
            for (int i = 0; i < fieldList.size(); i++) {
                Champ fld = fieldList.get(i);
                String end = i == fieldList.size() - 1 ? " //" : ", //";
                if (fld.isRef || fld.isPere) {
                    f.L________________________(fld.jtype, "Dto.asRef(entity.get", fld.uname, "())", end);
                } else {
                    f.L________________________("entity.get", fld.uname, "()", end);

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
            f.L________________________("entity.get", idChamp.uname, "(), //");
        }
        for (int i = 0; i < fieldList.size(); i++) {
            Champ fld = fieldList.get(i);
            String end = i == fieldList.size() - 1 ? " //" : ", //";
            if (fld.isId) {
                f.L________________________("entity.get", fld.uname, "()", end);
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
