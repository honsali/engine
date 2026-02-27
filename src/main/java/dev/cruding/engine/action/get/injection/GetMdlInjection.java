package dev.cruding.engine.action.get.injection;

import dev.cruding.engine.flow.MdlFlow;
import dev.cruding.engine.injection.ActionMdlInjection;

public class GetMdlInjection extends ActionMdlInjection {

    public void addMdlImport(MdlFlow f) {
        f.addMdlImport("{ I" + entity().uname + " }", "modele/" + entity().path + "/Domaine" + entity().uname);
    }

    public void addMdlRequestAttribute(MdlFlow f) {
        if (!asList()) {
            f.addMdlRequestAttribute("id" + entity().uname, "string");
        }
        if (byFatherId() && entity().haveFather) {
            f.addMdlRequestAttribute("id" + entity().ufather, "string");
        }
    }

    public void addMdlResultAttribute(MdlFlow f) {
        if (asList()) {
            f.addMdlResultAttribute("liste" + entity().uname, "I" + entity().uname + "[]");
        } else {
            f.addMdlResultAttribute(entity().lname, "I" + entity().uname);
        }
    }

    public void addMdlStateAttribute(MdlFlow f) {
        if (asList()) {
            f.addMdlStateAttribute("liste" + entity().uname, "I" + entity().uname + "[]");
            f.addMdlSelectorAttribute("liste" + entity().uname, "Liste" + entity().uname);
        } else {
            f.addMdlStateAttribute(entity().lname, "I" + entity().uname);
            f.addMdlSelectorAttribute(entity().lname, entity().uname);
        }
    }

    public void addMdlExtraReducerAffectation(MdlFlow f) {
        if (asList()) {
            f.L________________("state.liste", entity().uname, " = action.payload.liste", entity().uname, ";");
        } else {
            f.L________________("state.", entity().lname, " = action.payload.", entity().lname, ";");
        }
    }

}
