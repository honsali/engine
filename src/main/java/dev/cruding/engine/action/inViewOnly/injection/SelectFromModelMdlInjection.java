package dev.cruding.engine.action.inViewOnly.injection;

import dev.cruding.engine.flow.MdlFlow;
import dev.cruding.engine.injection.ActionMdlInjection;

public class SelectFromModelMdlInjection extends ActionMdlInjection {


    public void addMdlImport(MdlFlow f) {
        f.addMdlImport("{ I" + entity().uname + " }", "modele/" + entity().path + "/Domaine" + entity().uname);
        f.addMdlImport("{ PayloadAction }", "@reduxjs/toolkit");
    }

    public void addMdlRequestAttribute(MdlFlow f) {}

    public void addMdlResultAttribute(MdlFlow f) {}

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

    public boolean addMdlReducer(MdlFlow f) {
        f.L________("modifierListe", entity().uname, "(state, action: PayloadAction<I", entity().uname, "[]>) {");
        f.L____________("state.liste", entity().uname, " = action.payload;");
        f.L________("},");
        return false;
    }
}
