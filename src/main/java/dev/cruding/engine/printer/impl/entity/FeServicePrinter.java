package dev.cruding.engine.printer.impl.entity;

import java.util.List;
import dev.cruding.engine.action.Action;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.flow.Flow;
import dev.cruding.engine.gen.Context;
import dev.cruding.engine.printer.Printer;

public class FeServicePrinter extends Printer {

    public void print(Entity entity) {
        Flow f = new Flow();
        List<Action> actionList = Context.getInstance().actionEntity(entity);

        /* *********************************************************************** */
        f.__("import axios from 'axios';");
        f.L("import { API_URL } from 'commun/config/constants.config';");
        f.L("import { I", entity.uname, " } from './Domaine", entity.uname, "';");
        f.L("");
        f.L("const resourceUri = API_URL + '/", entity.lname, "';");

        for (Action action : actionList) {
            action.addServicImplementation(f);
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
        printFile(s, getBasePath() + "/fe/src/modele/" + entity.modulePath + "/Service" + entity.uname + ".ts");
    }

}
