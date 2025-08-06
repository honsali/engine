package dev.cruding.engine.printer.impl.module;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import dev.cruding.engine.action.Action;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.Contexte;
import dev.cruding.engine.gen.Module;
import dev.cruding.engine.gen.Page;
import dev.cruding.engine.printer.Printer;

public class FeAclModule extends Printer {

    public void print(Module module) {
        ViewFlow f = new ViewFlow();

        /* *********************************************************************** */
        List<Page> listePage = new ArrayList<>(Contexte.getInstance().getPageList(module));
        Collections.sort(listePage);
        f.addJsImport("{ Action" + module.unameLast + " }", module.path + "/Action" + module.unameLast);
        f.flushJsImportBloc();
        f.L("");
        f.L("");
        f.__("export const acl", module.unameLast, " = [");
        f.L("");
        for (Page page : listePage) {
            if (page.estReelle()) {


                List<Action> listeAction = new ArrayList<>(Contexte.getInstance().actionPage(page));
                for (Action action : listeAction) {
                    if (!action.actionKey.startsWith("#")) {
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

}
