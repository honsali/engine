package dev.cruding.engine.printer.impl.entite;

import java.util.HashSet;
import java.util.List;
import dev.cruding.engine.action.Action;
import dev.cruding.engine.entite.Entite;
import dev.cruding.engine.flow.JsFlow;
import dev.cruding.engine.gen.Contexte;
import dev.cruding.engine.printer.Printer;

public class FeServicePrinter extends Printer {

    public void print(Entite entite) {
        JsFlow f = new JsFlow();
        List<Action> actionList = Contexte.getInstance().actionEntite(entite);

        /* *********************************************************************** */

        f.addJsImport("{ API_URL }", "commun");
        f.addJsImport("axios", "axios");
        for (Action action : actionList) {
            action.serviceActionInjection.addServiceImport(f);
        }

        f.flushJsImportBloc();

        f.L("");

        HashSet<String> actionName = new HashSet<>();
        for (Action action : actionList) {
            if (!actionName.contains(action.lnameSansEntite)) {
                action.serviceActionInjection.addServiceImplementation(f);
                actionName.add(action.lnameSansEntite);
            }
        }
        f.L("");
        f.L("const Service", entite.uname, " = {");
        actionName = new HashSet<>();
        for (Action action : actionList) {
            if (!actionName.contains(action.lnameSansEntite)) {
                action.serviceActionInjection.addServiceDeclaration(f);
                actionName.add(action.lnameSansEntite);
            }
        }
        f.L("};");
        f.L("");
        f.L("export default Service", entite.uname, ";");

        /* *********************************************************************** */
        String s = f.toString();
        printFile(s, getBasePath() + "/fe/src/modele/" + entite.path + "/" + "/Service" + entite.uname + ".ts");
    }

}
