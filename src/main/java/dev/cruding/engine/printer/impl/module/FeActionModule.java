package dev.cruding.engine.printer.impl.module;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import dev.cruding.engine.action.Action;
import dev.cruding.engine.component.Component;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.Context;
import dev.cruding.engine.gen.Module;
import dev.cruding.engine.gen.Page;
import dev.cruding.engine.printer.Printer;

public class FeActionModule extends Printer {

    public void print(Module module) {
        ViewFlow f = new ViewFlow();

        /* *********************************************************************** */
        List<Page> listePage = new ArrayList<>(Context.getInstance().getPageList(module));
        Collections.sort(listePage);
        f.__("export const Action", module.unameLast, " = {");
        for (Page page : listePage) {
            f.L____("Uc", page.uc, ": {");
            List<Action> listeAction = Context.getInstance().actionPage(page);
            for (Action action : listeAction) {
                action.addActionModule(f, page);
            }
            for (Component action : page.listeActionInView) {
                action.addActionModule(f, page);
            }
            f.L____("},");

        }


        f.L("};");
        /* *********************************************************************** */

        String s = f.toString();
        printFile(s, getBasePath() + "/fe/src/" + module.path + "/Action" + module.unameLast + ".ts");
    }

}
