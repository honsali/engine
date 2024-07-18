package dev.cruding.engine.printer.impl.commun;

import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.flow.Flow;
import dev.cruding.engine.printer.Printer;

public class FeGoToPrinter extends Printer {

    public void print() {
        Flow f = new Flow();

        f.__("import _ from 'core/config/lodash.config';");
        f.L("import { useNavigate } from 'react-router';");
        f.L("");
        f.L("const pageMap = {");
        for (Entity e : entityList()) {
            f.L("    PageConsulter", e.uname, ": _.template('/", e.lname, "/<%=id%>/consulter'),");
            f.L("    PageLister", e.uname, ": _.template('/", e.lname, "/lister'),");
            f.L("    PageChercher", e.uname, ": _.template('/", e.lname, "/chercher'),");
            f.L("    PageCreer", e.uname, ": _.template('/", e.lname, "/creer'),");
            f.L("    PageModifier", e.uname, ": _.template('/", e.lname, "/<%=id%>/modifier'),");
        }
        f.L("};");
        f.L("");
        f.L("export default function useGoTo() {");
        f.L("    const nav = (page, modele?) => {");
        f.L("        navigate(pageMap[page](modele));");
        f.L("    };");
        f.L("");
        f.L("    const navigate = useNavigate();");
        f.L("    return nav;");
        f.L("}");

        String s = f.toString();
        printFile(s, getBasePath() + "/fe/src/core/util/useGoTo.ts");
    }

}