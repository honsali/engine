package dev.cruding.engine.printer.impl.entity;

import java.util.HashSet;
import java.util.List;
import dev.cruding.engine.action.Action;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.flow.JsFlow;
import dev.cruding.engine.gen.Context;
import dev.cruding.engine.printer.Printer;

public class FeServicePrinter extends Printer {

    public void print(Entity entity) {

        if (entity.isReferenceData()) {
            return;
        }

        JsFlow f = new JsFlow();
        List<Action> actionList = Context.getInstance().actionEntity(entity);


        /* *********************************************************************** */

        f.addJsImport("{ API_URL }", "commun");
        f.addJsImport("axios", "axios");
        for (Action action : actionList) {
            action.serviceActionInjection.addServiceImport(f);
        }

        f.flushJsImportBlock();

        f.L("");

        HashSet<String> actionName = new HashSet<>();
        for (Action action : actionList) {
            if (!actionName.contains(action.lnameWithoutEntity)) {
                action.serviceActionInjection.addServiceImplementation(f);
                actionName.add(action.lnameWithoutEntity);
            }
        }
        f.L("");
        f.L("const Service", entity.uname, " = {");
        actionName = new HashSet<>();
        for (Action action : actionList) {
            if (!actionName.contains(action.lnameWithoutEntity)) {
                action.serviceActionInjection.addServiceDeclaration(f);
                actionName.add(action.lnameWithoutEntity);
            }
        }
        f.L("};");
        f.L("");
        f.L("export default Service", entity.uname, ";");

        /* *********************************************************************** */
        String s = f.toString();
        printFile(s, getBasePath() + "/fe/src/modele/" + entity.path + "/" + "/Service" + entity.uname + ".ts");
    }

}
