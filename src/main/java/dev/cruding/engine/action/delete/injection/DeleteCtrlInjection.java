package dev.cruding.engine.action.delete.injection;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.flow.CtrlFlow;
import dev.cruding.engine.injection.ActionCtrlInjection;

public class DeleteCtrlInjection extends ActionCtrlInjection {

    public void addCtrlImport(CtrlFlow f) {
        f.addCtrlImport("Service" + entity().uname, "modele/" + entity().path + "/Service" + entity().uname);
    }


    public void addCtrlImplementationCore(CtrlFlow f) {
        f.L____("await Service", entity().uname, ".", lnameWithoutEntity(), "(");
        if (byFatherId()) {
            f.__("requete.id" + entity().ufather, ", ");
        }
        f.__("requete.id", entity().uname, ");");


        if (hasSuccess()) {
            for (Action ifSuccessAction : onSuccess()) {
                // if (ifSuccessAction.type.equals(ActionType.FLOW)) {
                if (!ifSuccessAction.inViewOnly) {
                    f.L____("await ", ifSuccessAction.lnameWithEntity, "Impl(requete, resultat, thunkAPI);");
                }
                // }
            }
        }

    }
}
