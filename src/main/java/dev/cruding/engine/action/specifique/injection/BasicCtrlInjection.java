package dev.cruding.engine.action.specifique.injection;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.field.Field;
import dev.cruding.engine.flow.CtrlFlow;
import dev.cruding.engine.injection.ActionCtrlInjection;

public class BasicCtrlInjection extends ActionCtrlInjection {

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
        if (byForm()) {
            f.L____("await requete.form?.validateFields();");
            f.L____("const dataForm = util.removeNonSerialisable(requete.form?.getFieldsValue());");
        }

        f.L____("");
        if (resultIn() != null) {
            f.__("resultat.", resultIn().lname, entity().uname, " = ");
        }
        f.__("await Service", entity().uname, ".", lnameWithoutEntity(), "(");
        boolean withComma = false;
        if (byFatherId() && entity().haveFather) {
            f.__("requete.id" + entity().ufather, ", ");
            withComma = true;
        }
        if (byId()) {
            f.__("requete.id", entity().uname, ", ");
            withComma = true;
        }
        if (byField() != null) {
            for (Field c : byField()) {
                f.__("requete.", c.lname, ", ");
            }
            withComma = true;
        }
        if (byForm()) {
            if (byEntity()) {
                f.__("{ ...", entity().lname, ", ...dataForm },");
            } else {
                f.__("dataForm, ");
            }
            withComma = true;
        }
        if (withComma) {
            f.removeAfterLastComma();
        }
        f.__(");");
        if (reload()) {
            f.L____("resultat.", entity().lname, " = await Service", entity().uname, ".recupererParId(");
            if (byFatherId() && entity().haveFather) {
                f.__("requete.id" + entity().ufather, ", ");
            }
            f.__("requete.id", entity().uname, ");");
        }


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
