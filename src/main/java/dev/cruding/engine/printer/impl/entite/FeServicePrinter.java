package dev.cruding.engine.printer.impl.entite;

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
            action.addServiceImport(f);
        }

        f.flushJsImportBloc();

        f.L("");
        f.L("const resourceUri = API_URL + '/", entite.lname, "';");

        for (Action action : actionList) {
            action.addServiceImplementation(f);
        }
        f.L("");
        f.L("const Service", entite.uname, " = {");

        for (Action action : actionList) {
            action.addServiceDeclaration(f);
        }
        f.L("};");
        f.L("");
        f.L("export default Service", entite.uname, ";");

        /* *********************************************************************** */
        String s = f.toString();
        printFile(s, getBasePath() + "/fe/src/modele/" + entite.path + "/" + "/Service" + entite.uname + ".ts");
    }

}
