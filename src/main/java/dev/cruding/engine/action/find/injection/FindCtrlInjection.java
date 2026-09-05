package dev.cruding.engine.action.find.injection;

import dev.cruding.engine.flow.CtrlFlow;
import dev.cruding.engine.injection.ActionCtrlInjection;

public class FindCtrlInjection extends ActionCtrlInjection {



    public void addCtrlImport(CtrlFlow f) {
        f.addCtrlImport("Service" + entity().uname, "modele/" + entity().path + "/Service" + entity().uname);
    }

    public void addCtrlImplementationCore(CtrlFlow f) {
        f.L____("resultat.", entity().lname, " = await Service", entity().uname, ".", lnameWithoutEntity(), "(requete.request);");
    }
}
