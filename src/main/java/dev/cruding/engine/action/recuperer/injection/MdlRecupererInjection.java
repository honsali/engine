package dev.cruding.engine.action.recuperer.injection;

import dev.cruding.engine.flow.MdlFlow;
import dev.cruding.engine.injection.MdlActionInjection;

public class MdlRecupererInjection extends MdlActionInjection {


    public void addMdlImport(MdlFlow f) {
        f.addMdlImport("{ I" + entite().uname + " }", "modele/" + entite().path + "/Domaine" + entite().uname);
    }

    public void addMdlRequestAttribute(MdlFlow f) {
        f.addMdlRequestAttribute("id" + entite().uname, "string");
        if (parIdGrandPere() && entite().haveGrandPere) {
            f.addMdlRequestAttribute("id" + entite().ugrandPere, "string");
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
    }

    public void addMdlSelector(MdlFlow f) {
        f.L("export const select", entite().uname, " = createSelector([selectMdl", uc(), "], (state: ", uc(), "Type) => state.", entite().lname, ");");
    }

    public void addMdlExtraReducer(MdlFlow f) {
        f.L____________(".addCase(Ctrl", uc(), ".", lnameAvecEntite(), ".fulfilled, (state, action) => {");
        f.L________________("state.resultat = action.payload;");
        f.L________________("state.", entite().lname, " = action.payload.", entite().lname, ";");
        f.L____________("})");
    }

}
