package dev.cruding.engine.printer.impl.module;

import java.util.ArrayList;
import java.util.List;
import dev.cruding.engine.action.Action;
import dev.cruding.engine.flow.JsFlow;
import dev.cruding.engine.gen.Context;
import dev.cruding.engine.gen.Module;
import dev.cruding.engine.gen.Page;
import dev.cruding.engine.printer.Printer;

public class FeAclPrinter extends Printer {

    public void print(Module module) {
        JsFlow f = new JsFlow();

        /* *********************************************************************** */
        ArrayList<Page> pageList = sortedPageList(module);
        boolean containsAclAction = hasAclAction(pageList);
        if (containsAclAction) {
            f.addJsImport("{ Action" + module.unameLast + " }", module.path + "/Action" + module.unameLast);
        }
        f.flushJsImportBlock();
        if (containsAclAction) {
            f.L("");
            f.L("");
        }
        f.__("export const acl", module.unameLast, " = [");
        f.L("");
        for (Page page : pageList) {
            if (page.containsComponent()) {
                List<Action> actionList = new ArrayList<>(Context.getInstance().actionPage(page));
                for (Action action : actionList) {
                    if (!action.noUi() && !action.flow()) {
                        f.L____("Action", module.unameLast, ".Uc", page.uc, ".", action.actionKey, ",//");
                    }
                }
                f.L("");
            }
        }


        f.L("];");
        /* *********************************************************************** */

        String s = f.toString();
        printFile(s, getBasePath() + "/fe/src/commun/securite/acl/acl" + module.unameLast + ".ts");
    }

    private boolean hasAclAction(List<Page> pageList) {
        for (Page page : pageList) {
            if (!page.containsComponent()) {
                continue;
            }
            for (Action action : Context.getInstance().actionPage(page)) {
                if (!action.noUi() && !action.flow()) {
                    return true;
                }
            }
        }
        return false;
    }

}
