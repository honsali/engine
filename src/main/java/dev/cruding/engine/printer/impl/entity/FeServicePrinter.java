package dev.cruding.engine.printer.impl.entity;

import java.util.List;
import dev.cruding.engine.action.Action;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.flow.JsFlow;
import dev.cruding.engine.gen.Context;
import dev.cruding.engine.printer.Printer;

public class FeServicePrinter extends Printer {

    public void print(Entity entity) {
        JsFlow f = new JsFlow();
        List<Action> actionList = Context.getInstance().actionEntity(entity);

        /* *********************************************************************** */

        f.addJsImport("{ API_URL }", "commun");
        f.addJsImport("axios", "axios");
        for (Action action : actionList) {
            action.addServiceImport(f);
        }

        f.flushJsImportBloc();

        f.L("");
        f.L("const resourceUri = API_URL + '/", entity.lname, "';");

        for (Action action : actionList) {
            action.addServiceImplementation(f);
        }
        f.L("");
        f.L("const Service", entity.uname, " = {");

        for (Action action : actionList) {
            action.addServiceDeclaration(f);
        }
        f.L("};");
        f.L("");
        f.L("export default Service", entity.uname, ";");

        /* *********************************************************************** */
        String s = f.toString();
        printFile(s, getBasePath() + "/fe/src/modele/" + entity.path + "/" + "/Service" + entity.uname + ".ts");
    }

}
