package dev.cruding.engine.printer.impl.module;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import dev.cruding.engine.action.Action;
import dev.cruding.engine.flow.Flow;
import dev.cruding.engine.gen.Context;
import dev.cruding.engine.gen.Module;
import dev.cruding.engine.gen.Page;
import dev.cruding.engine.printer.Printer;

public class FeActionPrinter extends Printer {

    public void print(Module module) {
        Flow f = new Flow();

        /* *********************************************************************** */
        ArrayList<Page> pageList = sortedPageList(module);
        f.__("export const Action", module.unameLast, " = {");
        for (Page page : pageList) {
            if (page.containsComponent()) {

                f.L____("Uc", page.uc, ": {");

                List<Action> actionList = Context.getInstance().actionPage(page);
                for (Action action : actionList) {
                    f.L________(action.actionKey, ": 'Uc", page.uc, ".action." + StringUtils.uncapitalize(action.lnameWithEntity), "',");
                }
                f.L____("},");
            }
        }


        f.L("};");
        /* *********************************************************************** */

        String s = f.toString();
        printFile(s, getBasePath() + "/fe/src/" + module.path + "/Action" + module.unameLast + ".ts");
    }

}
