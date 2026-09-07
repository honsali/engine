package dev.cruding.engine.printer.impl.module;

import java.util.ArrayList;
import dev.cruding.engine.flow.Flow;
import dev.cruding.engine.gen.Module;
import dev.cruding.engine.gen.Page;
import dev.cruding.engine.printer.Printer;

public class FeReducerPrinter extends Printer {

    public void print(Module module) {
        Flow f = new Flow();

        /* *********************************************************************** */
        ArrayList<Page> pageList = sortedPageList(module);
        int idx = module.path.length();
        boolean first = true;
        for (Page page : pageList) {
            if (page.containsComponent()) {
                if (first) {
                    first = false;
                } else {
                    f.L();
                }
                f.__("import Mdl", page.uc, " from '.", page.path.substring(idx), "/Mdl", page.uc, "';");
            }
        }
        f.L("");
        f.L("const Reducer", module.unameLast, " = {");
        for (Page page : pageList) {
            if (page.containsComponent()) {
                f.L____("mdl", page.uc, ": Mdl", page.uc, ",");
            }
        }
        f.L("};");
        f.L("");
        f.L("export default Reducer", module.unameLast, ";");
        /* *********************************************************************** */

        String s = f.toString();
        printFile(s, getBasePath() + "/fe/src/" + module.path + "/Reducer" + module.unameLast + ".tsx");
    }
}
