package dev.cruding.engine.printer.impl.element;

import java.util.List;
import dev.cruding.engine.action.Action;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.Context;
import dev.cruding.engine.printer.Printer;

public class FeElementPrinter extends Printer {


    public void print(Element element) {
        ViewFlow f = new ViewFlow(element);

        /* *********************************************************************** */
        if (element.byForm) {
            f.addProp("form");
        }
        f.addProp(element.byProp);

        List<Action> actionList = Context.getInstance().actionElement(element);

        for (int i = 0; i < actionList.size(); i++) {
            Action action = actionList.get(i);
            boolean retourLigne = action.viewActionInjection.addViewScript(f);
            if (retourLigne && i < actionList.size() - 1) {
                f.totalScript().L();
            }
        }
        element.addContent(f);

        f.flushViewImportBlock();
        f.L("");
        f.L("const ", element.name, " = (", f.joinProps(), ") => {");
        f.flushInitBlock();
        f.flushScriptBlock();
        f.L____("//");
        f.L____("return ");
        f.flushUiBlock();
        f.L("};");
        f.L("");
        f.L("export default ", element.name, ";");

        /* *********************************************************************** */

        String s = f.toString();
        printFile(s, getBasePath() + "/fe/src/" + element.path + "/" + element.name + ".tsx");
    }
}
