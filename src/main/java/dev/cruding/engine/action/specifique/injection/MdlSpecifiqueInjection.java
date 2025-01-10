package dev.cruding.engine.action.specifique.injection;

import dev.cruding.engine.flow.MdlFlow;
import dev.cruding.engine.injection.MdlActionInjection;

public class MdlSpecifiqueInjection extends MdlActionInjection {


    public void addMdlImport(MdlFlow f) {
        f.addMdlImport("{ I" + entite().uname + " }", "modele/" + entite().path + "/Domaine" + entite().uname);
        if (child() != null) {
            f.addMdlRequestAttribute("liste" + child().uname, "I" + child().uname + "[]");
            f.addMdlImport("{ I" + child().uname + " }", "modele/" + child().lname + "/Domaine" + child().uname);
            f.addMdlImport("{ PayloadAction }", "@reduxjs/toolkit");
        }
    }

    public void addMdlRequestAttribute(MdlFlow f) {
        if (byForm()) {
            f.addMdlRequestAttribute("form", "any");
        }
        if (child() != null) {
            f.addMdlRequestAttribute("liste" + child().uname, "I" + child().uname + "[]");
        }
        if (parIdGrandPere() && entite().haveGrandPere) {
            f.addMdlRequestAttribute("id" + entite().ugrandPere, "string");
        }
        if (parIdPere() && entite().havePere) {
            f.addMdlRequestAttribute("id" + entite().upere, "string");
        }

        if (byId()) {
            f.addMdlRequestAttribute("id" + entite().uname, "string");
        }
    }

    public void addMdlResultAttribute(MdlFlow f) {
        f.addMdlResultAttribute(entite().lname, "I" + entite().uname);
        if (resultatInId()) {
            f.addMdlResultAttribute("id" + entite().uname, "string");
        }

    }

    public void addMdlStateAttribute(MdlFlow f) {
        f.addMdlStateAttribute(entite().lname, "I" + entite().uname);
        if (child() != null) {
            f.addMdlStateAttribute("liste" + child().uname, "I" + child().uname + "[]");
        }
    }


    public boolean addMdlReducer(MdlFlow f) {
        if (child() != null) {
            f.L________("setListe", child().uname, ": (state, action: PayloadAction<I", child().uname, "[]>) => {");
            f.L________________("state.liste", child().uname, " = action.payload;");
            f.L________("},");
            return true;
        }
        return false;
    }

    public void addMdlExtraReducer(MdlFlow f) {
        f.L____________(".addCase(Ctrl", uc(), ".", lname(), ".fulfilled, (state, action) => {");
        f.L________________("state.resultat = action.payload;");
        if (recharger()) {
            f.L________________("state.", entite().lname, " = action.payload.", entite().lname, ";");
        }
        f.L____________("})");
    }

}
