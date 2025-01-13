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
    }

    public void addMdlSelector(MdlFlow f) {
        f.L("export const selectListe", entite().uname, " = createSelector([selectMdl", uc(), "], (state: ", uc(), "Type) => state.liste", entite().uname, ");");
    }

    public void addMdlExtraReducer(MdlFlow f) {
        f.L____________(".addCase(Ctrl", uc(), ".", lnameAvecEntite(), ".fulfilled, (state, action) => {");
        f.L________________("state.resultat = action.payload;");
        f.L________________("state.liste", entite().uname, " = action.payload.liste", entite().uname, ";");
        f.L____________("})");
    }

}
