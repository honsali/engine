package dev.cruding.engine.printer.impl.element;

import java.util.List;
import dev.cruding.engine.action.Action;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.Contexte;
import dev.cruding.engine.printer.Printer;

public class FeElementPrinter extends Printer {


    public void print(Element element) {
        ViewFlow f = new ViewFlow(element);

        /* *********************************************************************** */
        if (element.parForm) {
            f.addProp("form");
        }
        f.addProp(element.parProp);

        List<Action> listeAction = Contexte.getInstance().actionElement(element);

        for (Action action : listeAction) {
            if (action.viewActionInjection.addViewScript(f)) {
                f.totalScript().L("");
            }
        }
        element.addContent(f);
        /* *********************************************************************** */

        f.flushViewImportBloc();
        f.L("");
        f.L("const ", element.name, " = (", f.joinProps(), ") => {");
        f.flushInitBloc();
        f.flushScriptBloc();
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
