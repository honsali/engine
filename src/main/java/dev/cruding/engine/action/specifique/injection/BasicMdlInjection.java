package dev.cruding.engine.action.specifique.injection;

import java.util.ArrayList;
import dev.cruding.engine.action.Action;
import dev.cruding.engine.field.Field;
import dev.cruding.engine.flow.MdlFlow;
import dev.cruding.engine.injection.ActionMdlInjection;

public class BasicMdlInjection extends ActionMdlInjection {

    public void addMdlImport(MdlFlow f) {
        if (byEntity()) {
            f.addMdlImport("{ I" + entity().uname + " }", "modele/" + entity().path + "/Domaine" + entity().uname);
        }
        if (byForm()) {
            f.addMdlImport("{ FormInstance }", "antd");
        }
    }

    public void addMdlRequestAttribute(MdlFlow f) {
        if (byForm()) {
            f.addMdlRequestAttribute("form", "FormInstance");
        }
        if (byFatherId() && entity().haveFather) {
            f.addMdlRequestAttribute("id" + entity().ufather, "string");
        }

        if (byId()) {
            f.addMdlRequestAttribute("id" + entity().uname, "string");
        }

        if (byField() != null) {
            for (Field c : byField()) {
                f.addMdlRequestAttribute(c.lname, "string");
            }

        }
    }

    public void addMdlResultAttribute(MdlFlow f) {
        if (byEntity()) {
            f.addMdlResultAttribute(entity().lname, "I" + entity().uname);
        }
        if (resultIn() != null) {
            f.addMdlResultAttribute(resultIn().lname + entity().uname, resultIn().jstype);
        }

    }

    public void addMdlStateAttribute(MdlFlow f) {
        if (byEntity()) {
            f.addMdlStateAttribute(entity().lname, "I" + entity().uname);
            f.addMdlSelectorAttribute(entity().lname, entity().uname);
        }
    }


    public void addMdlExtraReducerAffectation(MdlFlow f) {

        if (hasSuccess()) {
            ArrayList<Action> ifSuccessActionList = onSuccess();
            if (ifSuccessActionList.size() > 0) {
                for (Action ifSuccessAction : ifSuccessActionList) {
                    if (ifSuccessAction.mdlActionInjection != null) {
                        ifSuccessAction.mdlActionInjection.addMdlExtraReducerAffectation(f);
                    }
                    if (ifSuccessAction.resultIn != null) {
                        f.L________________("state." + ifSuccessAction.resultIn.lname + ifSuccessAction.entity.uname + " = action.payload." + ifSuccessAction.resultIn.lname + ifSuccessAction.entity.uname + ";");
                    }
                }
            }
        }
    }
}
