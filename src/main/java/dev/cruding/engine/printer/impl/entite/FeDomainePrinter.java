package dev.cruding.engine.printer.impl.entite;

import dev.cruding.engine.champ.Champ;
import dev.cruding.engine.entite.Entite;
import dev.cruding.engine.flow.JsFlow;
import dev.cruding.engine.printer.Printer;

public class FeDomainePrinter extends Printer {

    public void print(Entite entite) {
        JsFlow f = new JsFlow();

        /* *********************************************************************** */
        for (Champ fld : entite.fieldList) {
            fld.addJsImport(f, entite);
        }
        f.addJsImport("{ IPagination }", "modele/commun/pagination/DomainePagination");
        f.flushJsImportBloc();
        f.L("");
        f.L("export interface I", entite.uname, " {");
        f.L____("id?: number;");
        f.L____("id", entite.uname, "?: number;");
        for (Champ fld : entite.fieldList) {
            fld.addJsDeclaration(f);
        }
        f.L("}");
        f.L("");

        f.L("export interface IRequete", entite.uname, " extends I", entite.uname, ", IPagination { }");
        f.L("export interface IListePaginee", entite.uname, " {");
        f.L____("liste?: I", entite.uname, "[];");
        f.L____("pagination?: IPagination;");
        f.L("}");

        /* *********************************************************************** */
        String s = f.toString();
        printFile(s, getBasePath() + "/fe/src/modele/" + entite.path + "/Domaine" + entite.uname + ".ts");
    }

}
