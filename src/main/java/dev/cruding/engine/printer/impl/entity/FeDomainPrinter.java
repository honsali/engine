package dev.cruding.engine.printer.impl.entity;

import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.field.Field;
import dev.cruding.engine.flow.JsFlow;
import dev.cruding.engine.printer.Printer;

public class FeDomainPrinter extends Printer {

    public void print(Entity entity) {
        JsFlow f = new JsFlow();

        /* *********************************************************************** */
        for (Field field : entity.fieldList) {
            field.addJsImport(f, entity);
        }
        if (!entity.isReferenceData()) {
            f.addJsImport("{ IPagination }", "modele/commun/pagination/DomainePagination");
        }
        if (f.flushJsImportBlock()) {
            f.L("");
            f.L("");
        }
        f.__("export interface I", entity.uname, " {");
        f.L____("id?: string;");
        f.L____("id", entity.uname, "?: string;");
        for (Field field : entity.fieldList) {
            field.addJsDeclaration(f);
        }
        f.L("}");
        f.L("");
        if (!entity.isReferenceData()) {
            f.L("export interface IRequete", entity.uname, " extends I", entity.uname, ", IPagination { }");
            f.L("export interface IListePaginee", entity.uname, " {");
            f.L____("liste?: I", entity.uname, "[];");
            f.L____("pagination?: IPagination;");
            f.L("}");
        }

        /* *********************************************************************** */
        String s = f.toString();
        printFile(s, getBasePath() + "/fe/src/modele/" + entity.path + "/Domaine" + entity.uname + ".ts");
    }

}
