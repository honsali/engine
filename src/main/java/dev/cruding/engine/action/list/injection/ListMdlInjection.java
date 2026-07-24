package dev.cruding.engine.action.list.injection;

import dev.cruding.engine.flow.MdlFlow;
import dev.cruding.engine.injection.ActionMdlInjection;

public class ListMdlInjection extends ActionMdlInjection {

    public void addMdlImport(MdlFlow f) {
        f.addMdlImport("{ I" + entity().uname + " }", "modele/" + entity().path + "/Domaine" + entity().uname);
    }

    public void addMdlRequestAttribute(MdlFlow f) {

        if (byFatherId() && entity().haveFather) {
            f.addMdlRequiredRequestAttribute("id" + entity().ufather, "string");
        }
    }

    public void addMdlResultAttribute(MdlFlow f) {
        f.addMdlResultAttribute("liste" + entity().uname, "I" + entity().uname + "[]");
    }

    public void addMdlStateAttribute(MdlFlow f) {
        f.addMdlStateAttribute("liste" + entity().uname, "I" + entity().uname + "[]");
        f.addMdlSelectorAttribute("liste" + entity().uname, "Liste" + entity().uname);
    }

    public void addUseSelector(MdlFlow f) {
        f.L________("liste", entity().uname, ",");
    }

    public void addMdlExtraReducerAffectation(MdlFlow f) {
        f.L________________("state.liste", entity().uname, " = action.payload.liste", entity().uname, ";");
    }
}
