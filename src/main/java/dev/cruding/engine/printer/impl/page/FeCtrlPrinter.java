package dev.cruding.engine.printer.impl.page;

import java.util.List;
import dev.cruding.engine.action.Action;
import dev.cruding.engine.flow.CtrlFlow;
import dev.cruding.engine.gen.Context;
import dev.cruding.engine.gen.Page;
import dev.cruding.engine.gen.Util;
import dev.cruding.engine.printer.Printer;

public class FeCtrlPrinter extends Printer {

    public void print(Page page) {

        CtrlFlow f = new CtrlFlow();
        List<Action> controllerActionList = Context.getInstance().actionPage(page).stream()
                .filter(action -> action.uc != null && !action.inViewOnly)
                .toList();
        /* *********************************************************************** */

        if (!controllerActionList.isEmpty()) {
            f.addCtrlImport("{ ActionOperation, action }", "waxant");
            f.addCtrlImport("{ Req" + page.uc + ", Res" + page.uc + " }", "./Mdl" + page.uc);
            String s = Util.getRelativePath(page.path, page.module.path, false);
            f.addCtrlImport("{ Action" + page.module.unameLast + " }", s + "/Action" + page.module.unameLast);
        }

        for (Action action : controllerActionList) {
            action.ctrlActionInjection.addCtrlImport(f);
        }

        f.flushCtrlImportBlock();

        for (Action action : controllerActionList) {
            action.ctrlActionInjection.addCtrlImplementation(f);
        }

        f.L("");
        f.L("const Ctrl", page.uc, " = {");
        for (Action action : controllerActionList) {
            action.ctrlActionInjection.addCtrlDeclaration(f);
        }
        f.L("};");

        f.L("");
        f.L("export default Ctrl", page.uc, ";");

        /* *********************************************************************** */
        String s = f.toString();
        printFile(s, getBasePath() + "/fe/src/" + page.path + "/Ctrl" + page.uc + ".ts");
    }
}
