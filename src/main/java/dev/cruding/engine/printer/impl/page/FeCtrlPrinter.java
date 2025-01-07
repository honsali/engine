package dev.cruding.engine.printer.impl.page;

import java.util.List;
import dev.cruding.engine.action.Action;
import dev.cruding.engine.flow.CtrlFlow;
import dev.cruding.engine.gen.Contexte;
import dev.cruding.engine.gen.Page;
import dev.cruding.engine.gen.helper.Util;
import dev.cruding.engine.printer.Printer;

public class FeCtrlPrinter extends Printer {

    public void print(Page page) {

        CtrlFlow f = new CtrlFlow();
        List<Action> actionList = Contexte.getInstance().actionPage(page);
        /* *********************************************************************** */

        f.addCtrlImport("{ action }", "waxant");
        f.addCtrlImport("{ Req" + page.uc + ", Res" + page.uc + " }", "./Mdl" + page.uc);

        if (actionList.size() > 0) {
            String s = Util.getRelativePath(page.path, page.module.path, false);
            f.addCtrlImport("{ Action" + page.module.unameLast + " }", s + "/Action" + page.module.unameLast);
        }

        for (Action action : actionList) {
            action.addCtrlImport(f);
        }

        f.flushCtrlImportBloc();

        for (Action action : actionList) {
            action.addCtrlImplementation(f);
        }

        f.L("");
        f.L("const Ctrl", page.uc, " = {");
        for (Action action : actionList) {
            action.addCtrlDeclaration(f, page);
        }
        f.L("};");

        f.L("");
        f.L("export default Ctrl", page.uc, ";");

        /* *********************************************************************** */
        String s = f.toString();
        printFile(s, getBasePath() + "/fe/src/" + page.path + "/Ctrl" + page.uc + ".ts");
    }
}
