package dev.cruding.engine.action.list.injection;

import dev.cruding.engine.flow.CtrlFlow;
import dev.cruding.engine.injection.ActionCtrlInjection;

public class ListCtrlInjection extends ActionCtrlInjection {

    public void addCtrlImport(CtrlFlow f) {
        f.addCtrlImport("Service" + entity().uname, "modele/" + entity().path + "/Service" + entity().uname);
    }

    public void addCtrlImplementationCore(CtrlFlow f) {
        f.L____("resultat.liste", entity().uname, " = await Service", entity().uname, ".", lnameWithoutEntity(), "(");

        if (byFatherId() && entity().haveFather) {
            f.__("requete.id", entity().ufather);
        }
        f.__(");");
    }
}
