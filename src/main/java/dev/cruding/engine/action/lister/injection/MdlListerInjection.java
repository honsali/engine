package dev.cruding.engine.action.lister.injection;

import dev.cruding.engine.flow.MdlFlow;
import dev.cruding.engine.injection.MdlActionInjection;

public class MdlListerInjection extends MdlActionInjection {

    public void addMdlImport(MdlFlow f) {
        f.addMdlImport("{ I" + entite().uname + " }", "modele/" + entite().path + "/Domaine" + entite().uname);
    }

    public void addMdlRequestAttribute(MdlFlow f) {
        if (parIdGrandPere() && entite().haveGrandPere) {
            f.addMdlRequestAttribute("id" + entite().ugrandPere, "string");
        }
        if (parIdPere() && entite().havePere) {
            f.addMdlRequestAttribute("id" + entite().upere, "string");
        }
    }

    public void addMdlResultAttribute(MdlFlow f) {
        f.addMdlResultAttribute("liste" + entite().uname, "I" + entite().uname + "[]");
    }

    public void addMdlStateAttribute(MdlFlow f) {
        f.addMdlStateAttribute("liste" + entite().uname, "I" + entite().uname + "[]");
        f.addMdlSelectorAttribute("liste" + entite().uname, "Liste" + entite().uname);
    }

    public void addUseSelector(MdlFlow f) {
        f.L________("liste", entite().uname, ",");
    }

    public void addMdlExtraReducerAffectation(MdlFlow f) {
        f.L________________("state.liste", entite().uname, " = action.payload.liste", entite().uname, ";");
    }
}
