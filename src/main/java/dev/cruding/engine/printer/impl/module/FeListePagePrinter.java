package dev.cruding.engine.printer.impl.module;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.Context;
import dev.cruding.engine.gen.Module;
import dev.cruding.engine.gen.Page;
import dev.cruding.engine.printer.Printer;

public class FeListePagePrinter extends Printer {

    public void print(Module module) {
        ViewFlow f = new ViewFlow();

        /* *********************************************************************** */
        List<Page> listePage = new ArrayList<>(Context.getInstance().getPageList(module));
        Collections.sort(listePage);
        int idx = module.path.length();
        f.addJsImport("IconeMenuCarre", "commun/composants/IconeMenuCarre");
        f.addJsImport("{ ContexteViewProvider, PageDefinition }", "waxant");
        for (Page page : listePage) {
            if (page.estReelle()) {
                f.addJsImport("View" + page.uc, "." + page.path.substring(idx) + "/View" + page.uc);
            }
        }
        f.flushJsImportBloc();
        f.L("");
        for (Page page : listePage) {
            if (page.estReelle()) {
                f.L("export const Page", page.uc, ": PageDefinition = {");
                f.L____("key: 'Page", page.uc, "',");
                f.L____("path: '/", getPath(module, page), "',");
                f.L____("toPath: (args) => '", getToPath(module, page), "',");
                f.L____("icone: <IconeMenuCarre />,");
                f.L____("view: (");
                f.L________("<ContexteViewProvider uc=\"Uc", page.uc, "\">");
                f.L____________("<View", page.uc, " />");
                f.L________("</ContexteViewProvider>");
                f.L____("),");
                f.L("};");
                f.L("");
            }

        }

        f.L("const ListePage", module.unameLast, " = [");
        int i = 0;
        for (Page page : listePage) {
            if (page.estReelle()) {
                f.L____("Page", page.uc, ",");
                if (i == 0) {
                    f.__(" //");
                    i++;
                }
            }
        }
        f.L("];");
        f.L("export default ListePage", module.unameLast, ";");
        /* *********************************************************************** */

        String s = f.toString();
        printFile(s, getBasePath() + "/fe/src/" + module.path + "/ListePage" + module.unameLast + ".tsx");
    }

    private String getPath(Module module, Page page) {
        return module.path.substring(8) + "/" + page.entiteLname + "/" + page.actionLname;
    }

    private String getToPath(Module module, Page page) {
        return module.path.substring(7) + "/" + page.entiteLname + "/" + page.actionLname;
    }
}
