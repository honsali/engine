package dev.cruding.engine.printer.impl.element;

import java.util.List;
import dev.cruding.engine.action.Action;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.Context;
import dev.cruding.engine.gen.Element;
import dev.cruding.engine.printer.Printer;

public class FeElementPrinter extends Printer {


    public void print(Element element) {
        ViewFlow f = new ViewFlow();

        /* *********************************************************************** */
        if (element.byForm) {
            f.addProp("form");
        }

        if (element.byProp != null) {
            f.addProp(element.byProp);
        }

        element.addContent(f);

        List<Action> listeAction = Context.getInstance().actionElement(element);
        for (Action action : listeAction) {
            if (action.addViewScript(f)) {
                f.totalScript().L("");
            }
        }
        f.cleanTotalScript();
        f.flushJsImportBloc();
        f.L("");
        f.L("const ", element.name, " = (", f.joinProps(), ") => {");
        boolean notEmpty = f.flushInitBloc();
        f.flushScriptBloc(notEmpty);
        f.L____("//");
        f.L____("return ");
        f.flushUiBloc();
        f.L("};");
        f.L("");
        f.L("export default ", element.name, ";");

        /* *********************************************************************** */

        String s = f.toString();
        printFile(s, getBasePath() + "/fe/src/" + element.path + "/" + element.name + ".tsx");
    }
}
