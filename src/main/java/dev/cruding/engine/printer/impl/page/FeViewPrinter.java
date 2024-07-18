package dev.cruding.engine.printer.impl.page;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.component.Component;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.Context;
import dev.cruding.engine.gen.Page;
import dev.cruding.engine.printer.Printer;

public class FeViewPrinter extends Printer {

    public void print(Page page) {
        ViewFlow f = new ViewFlow();

        /* *********************************************************************** */

        for (Component component : page.componentList) {
            component.addContent(null, f, 1);
        }

        for (Action action : Context.getInstance().actionPage(page)) {
            if (!action.isInElement()) {
                action.addViewScript(f);
            }
        }

        f.flushJsImportBloc();
        f.L("");
        f.L("const View", page.uc, " = (", f.joinProps(), ") => {");
        f.flushInitBloc(page);
        f.flushScriptBloc();
        f.L____("//");
        f.L____("return (");
        f.flushUiBloc();
        f.L____(");");
        f.L("};");
        f.L("");
        f.L("export default View", page.uc, ";");

        /* *********************************************************************** */

        String s = f.toString();
        printFile(s, getBasePath() + "/fe/src/" + page.path + "/View" + page.uc + ".tsx");
    }
}
