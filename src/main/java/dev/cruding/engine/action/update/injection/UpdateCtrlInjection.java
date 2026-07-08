package dev.cruding.engine.action.update.injection;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.flow.CtrlFlow;
import dev.cruding.engine.injection.ActionCtrlInjection;

public class UpdateCtrlInjection extends ActionCtrlInjection {

    public void addCtrlImport(CtrlFlow f) {
        f.addCtrlImport("Service" + entity().uname, "modele/" + entity().path + "/Service" + entity().uname);
        if (byForm()) {
            f.addCtrlImport("{ util }", "waxant");
        }
    }


    public void addCtrlImplementationCore(CtrlFlow f) {
        if (byEntity()) {
            f.L____("const { mdl", uc(), " } = thunkAPI.getState() as any;");
            f.L____("const ", entity().lname, " = mdl", uc(), ".", entity().lname, ";");
        }
        f.L____("await requete.form?.validateFields();");
        f.L____("const dataForm = util.removeNonSerialisable(requete.form?.getFieldsValue());");
        f.L____("await Service", entity().uname, ".", lnameWithoutEntity(), "(");
        if (byFatherId()) {
            f.__("requete.id" + entity().ufather, ", ");
        }
        f.__("dataForm");
        f.__(");");


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
