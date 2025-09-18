package dev.cruding.engine.action.recuperer.injection;

import dev.cruding.engine.flow.MdlFlow;
import dev.cruding.engine.injection.MdlActionInjection;

public class MdlRecupererInjection extends MdlActionInjection {

    public void addMdlImport(MdlFlow f) {
        f.addMdlImport("{ I" + entite().uname + " }", "modele/" + entite().path + "/Domaine" + entite().uname);
    }

    public void addMdlRequestAttribute(MdlFlow f) {
        if (!enTantQueListe()) {
            f.addMdlRequestAttribute("id" + entite().uname, "string");
        }
        if (parIdPere() && entite().havePere) {
            f.addMdlRequestAttribute("id" + entite().upere, "string");
        }
    }

    public void addMdlResultAttribute(MdlFlow f) {
        if (enTantQueListe()) {
            f.addMdlResultAttribute("liste" + entite().uname, "I" + entite().uname + "[]");
        } else {
            f.addMdlResultAttribute(entite().lname, "I" + entite().uname);
        }
    }

    public void addMdlStateAttribute(MdlFlow f) {
        if (enTantQueListe()) {
            f.addMdlStateAttribute("liste" + entite().uname, "I" + entite().uname + "[]");
            f.addMdlSelectorAttribute("liste" + entite().uname, "Liste" + entite().uname);
        } else {
            f.addMdlStateAttribute(entite().lname, "I" + entite().uname);
            f.addMdlSelectorAttribute(entite().lname, entite().uname);
        }
    }

    public void addMdlExtraReducerAffectation(MdlFlow f) {
        if (enTantQueListe()) {
            f.L________________("state.liste", entite().uname, " = action.payload.liste", entite().uname, ";");
        } else {
            f.L________________("state.", entite().lname, " = action.payload.", entite().lname, ";");
        }
    }

}
