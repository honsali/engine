package dev.cruding.engine.printer.impl.module;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import dev.cruding.engine.action.Action;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.Contexte;
import dev.cruding.engine.gen.Module;
import dev.cruding.engine.gen.Page;
import dev.cruding.engine.printer.Printer;

public class FeActionModule extends Printer {

    public void print(Module module) {
        ViewFlow f = new ViewFlow();

        /* *********************************************************************** */
        List<Page> listePage = new ArrayList<>(Contexte.getInstance().getPageList(module));
        Collections.sort(listePage);
        f.__("export const Action", module.unameLast, " = {");
        for (Page page : listePage) {
            if (page.estReelle()) {

                f.L____("Uc", page.uc, ": {");

                List<Action> listeAction = new ArrayList<>(Contexte.getInstance().allActionPage(page));
                for (Action action : listeAction) {
                    f.L________(action.actionKey, ": 'Uc", page.uc, ".action." + StringUtils.uncapitalize(action.lname), "',");
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
