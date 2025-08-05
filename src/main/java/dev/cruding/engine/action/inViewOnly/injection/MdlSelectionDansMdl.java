package dev.cruding.engine.action.inViewOnly.injection;

import dev.cruding.engine.flow.MdlFlow;
import dev.cruding.engine.injection.MdlActionInjection;

public class MdlSelectionDansMdl extends MdlActionInjection {


    public void addMdlImport(MdlFlow f) {
        f.addMdlImport("{ I" + entite().uname + " }", "modele/" + entite().path + "/Domaine" + entite().uname);
        f.addMdlImport("{ PayloadAction }", "@reduxjs/toolkit");
    }

    public void addMdlRequestAttribute(MdlFlow f) {}

    public void addMdlResultAttribute(MdlFlow f) {}

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

    public boolean addMdlReducer(MdlFlow f) {
        f.L________("modifierListe", entite().uname, "(state, action: PayloadAction<I", entite().uname, "[]>) {");
        f.L____________("state.liste", entite().uname, " = action.payload;");
        f.L________("},");
        return false;
    }
}
