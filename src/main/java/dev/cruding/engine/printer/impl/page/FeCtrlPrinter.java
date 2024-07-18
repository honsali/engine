package dev.cruding.engine.printer.impl.page;

import java.util.List;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.flow.MCFlow;
import dev.cruding.engine.gen.Context;
import dev.cruding.engine.gen.Page;
import dev.cruding.engine.printer.Printer;

public class FeCtrlPrinter extends Printer {

    public void print(Page page) {

        MCFlow f = new MCFlow();
        List<Action> actionList = Context.getInstance().actionPage(page);
        /* *********************************************************************** */
        f.addCtrlImport("{ action }", "waxant");
        f.addCtrlImport("{ Req" + page.uc + ", Res" + page.uc + " }", "./Mdl" + page.uc);
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
            action.addCtrlDeclaration(f);
        }
        f.L("};");

        f.L("");
        f.L("export default Ctrl", page.uc, ";");

        /* *********************************************************************** */
        String s = f.toString();
        printFile(s, getBasePath() + "/fe/src/" + page.path + "/Ctrl" + page.uc + ".ts");
    }
}