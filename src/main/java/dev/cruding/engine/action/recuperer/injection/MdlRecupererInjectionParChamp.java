package dev.cruding.engine.action.recuperer.injection;

import dev.cruding.engine.flow.MdlFlow;
import dev.cruding.engine.injection.MdlActionInjection;

public class MdlRecupererInjectionParChamp extends MdlActionInjection {



    public void addMdlImport(MdlFlow f) {
        f.addMdlImport("{ I" + entite().uname + " }", "modele/" + entite().path + "/Domaine" + entite().uname);
    }

    public void addMdlRequestAttribute(MdlFlow f) {
        String lnameChamp = parChamp()[0].lname;
        if (lnameChamp.equals("id")) {
            f.addMdlRequestAttribute("id" + entite().uname, "string");
        } else {
            f.addMdlRequestAttribute(lnameChamp, "string");
        }

        if (parIdPere() && entite().havePere) {
            f.addMdlRequestAttribute("id" + entite().upere, "string");
        }
    }

    public void addMdlResultAttribute(MdlFlow f) {
        f.addMdlResultAttribute(entite().lname, "I" + entite().uname);
    }

    public void addMdlStateAttribute(MdlFlow f) {
        f.addMdlStateAttribute(entite().lname, "I" + entite().uname);
        f.addMdlSelectorAttribute(entite().lname, entite().uname);

    }

    public void addMdlExtraReducerAffectation(MdlFlow f) {
        f.L________________("state.", entite().lname, " = action.payload.", entite().lname, ";");
    }

}
