package dev.cruding.engine.action.get.injection;

import dev.cruding.engine.flow.MdlFlow;
import dev.cruding.engine.injection.ActionMdlInjection;

public class GetByFieldMdlInjection extends ActionMdlInjection {



    public void addMdlImport(MdlFlow f) {
        f.addMdlImport("{ I" + entity().uname + " }", "modele/" + entity().path + "/Domaine" + entity().uname);
    }

    public void addMdlRequestAttribute(MdlFlow f) {
        String lnameField = byField()[0].lname;
        if (lnameField.equals("id")) {
            f.addMdlRequestAttribute("id" + entity().uname, "string");
        } else {
            f.addMdlRequestAttribute(lnameField, "string");
        }

        if (byFatherId() && entity().haveFather) {
            f.addMdlRequestAttribute("id" + entity().ufather, "string");
        }
    }

    public void addMdlResultAttribute(MdlFlow f) {
        f.addMdlResultAttribute(entity().lname, "I" + entity().uname);
    }

    public void addMdlStateAttribute(MdlFlow f) {
        f.addMdlStateAttribute(entity().lname, "I" + entity().uname);
        f.addMdlSelectorAttribute(entity().lname, entity().uname);

    }

    public void addMdlExtraReducerAffectation(MdlFlow f) {
        f.L________________("state.", entity().lname, " = action.payload.", entity().lname, ";");
    }

}
