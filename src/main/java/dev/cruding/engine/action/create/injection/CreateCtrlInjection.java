package dev.cruding.engine.action.create.injection;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.flow.CtrlFlow;
import dev.cruding.engine.injection.ActionCtrlInjection;

public class CreateCtrlInjection extends ActionCtrlInjection {

    public void addCtrlImport(CtrlFlow f) {
        f.addCtrlImport("Service" + entity().uname, "modele/" + entity().path + "/Service" + entity().uname);
    }


    public void addCtrlImplementationCore(CtrlFlow f) {
        f.L____("const { id } = await Service", entity().uname, ".", lnameWithoutEntity(), "(");
        if (byFatherId() && entity().haveFather) {
            f.__("requete.id" + entity().ufather, ", ");
        }
        f.__("requete.request);");
        f.L____("resultat.id", entity().uname, " = id;");



        if (hasSuccess()) {
            for (Action ifSuccessAction : onSuccess()) {
                if (!ifSuccessAction.inViewOnly) {
                    f.L____("await ", ifSuccessAction.lnameWithEntity, "Impl(requete, resultat, thunkAPI);");
                }
            }
        }
    }
}
