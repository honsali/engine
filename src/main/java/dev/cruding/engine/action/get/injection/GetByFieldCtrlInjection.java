package dev.cruding.engine.action.get.injection;

import dev.cruding.engine.flow.CtrlFlow;
import dev.cruding.engine.injection.ActionCtrlInjection;

public class GetByFieldCtrlInjection extends ActionCtrlInjection {


    public void addCtrlImport(CtrlFlow f) {
        f.addCtrlImport("Service" + entity().uname, "modele/" + entity().path + "/Service" + entity().uname);
    }

    public void addCtrlImplementationCore(CtrlFlow f) {
        String lnameField = byField()[0].lname;
        String unameField = byField()[0].uname;

        f.L____("resultat.", entity().lname, " = await Service", entity().uname, ".recupererPar", unameField, "(");

        if (byFatherId() && entity().haveFather) {
            f.__("requete.id" + entity().ufather, ", ");
        }
        f.__("requete.", lnameField.equals("id") ? "id" + entity().uname : lnameField, ");");
    }
}
