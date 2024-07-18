package dev.cruding.engine.printer.impl.commun;

import dev.cruding.engine.flow.Flow;
import dev.cruding.engine.gen.Page;
import dev.cruding.engine.printer.Printer;

public class FeRoutesPrinter extends Printer {

    public void print() {
        Flow f = new Flow();
        for (Page p : pageList()) {
            f.L("import View" + p.uc + " from 'modules/" + p.path + "/View" + p.uc + "';");
        }
        f.L("import { Suspense } from 'react';");
        f.L("import { Route } from 'react-router';");
        f.L("import AccueilAdmin from './home';");
        f.L("");
        f.L("const PageAccueilAdmin = (props) => (");
        f.L____("<Suspense fallback=\"\">");
        f.L________("<AccueilAdmin {...props} />");
        f.L____("</Suspense>");
        f.L(");");
        for (Page p : pageList()) {
            f.L("const Page" + p.uc + " = (props) => (");
            f.L____("<Suspense fallback=\"\">");
            f.L________("<View" + p.uc + " {...props} />");
            f.L____("</Suspense>");
            f.L(");");
        }
        f.L("const RoutesAdmin = () => {");
        f.L____("const map = [];");
        f.L____("map.push(<Route key=\"100\" index element={<PageAccueilAdmin />} />);");
        f.L____("map.push(<Route key=\"101\" path=\"/accueil\" element={<PageAccueilAdmin />} />);");
        int i = 3;
        for (Page p : pageList()) {
            f.L____("map.push(<Route key=\"10" + (i++) + "\" path=\"/" + p.route + "\" element={<Page" + p.uc + " />} />);");
        }
        f.L____("return map;");
        f.L("};");
        f.L("export default RoutesAdmin;");

        String s = f.toString();
        printFile(s, getBasePath() + "/fe/src/domaines/admin/routes.tsx");
    }
}