package dev.cruding.engine.action.listerEnPage.injection;

import dev.cruding.engine.flow.MdlFlow;
import dev.cruding.engine.injection.MdlActionInjection;

public class MdlListerEnPageInjection extends MdlActionInjection {

    public void addMdlImport(MdlFlow f) {
        f.addMdlImport("{ IListePaginee" + entite().uname + " }", "modele/" + entite().path + "/Domaine" + entite().uname);
    }

    public void addMdlRequestAttribute(MdlFlow f) {
        f.addMdlRequestAttribute("pageCourante", "number");
        if (parIdGrandPere() && entite().haveGrandPere) {
            f.addMdlRequestAttribute("id" + entite().ugrandPere, "string");
        }
        if (parIdPere() && entite().havePere) {
            f.addMdlRequestAttribute("id" + entite().upere, "string");
        }
    }

    public void addMdlResultAttribute(MdlFlow f) {
        f.addMdlResultAttribute("listePaginee" + entite().uname, "IListePaginee" + entite().uname);
    }

    public void addMdlStateAttribute(MdlFlow f) {
        f.addMdlStateAttribute("listePaginee" + entite().uname, "IListePaginee" + entite().uname);
    }

    public void addMdlSelector(MdlFlow f, String uc) {
        f.L("export const selectListePaginee", entite().uname, " = createSelector([selectMdl", uc(), "], (state: ", uc(), "Type) => state.listePaginee", entite().uname, ");");
    }

    public void addMdlExtraReducer(MdlFlow f) {
        f.L____________(".addCase(Ctrl", uc(), ".", lname(), ".fulfilled, (state, action) => {");
        f.L________________("state.resultat = action.payload;");
        f.L________________("state.listePaginee", entite().uname, " = action.payload.listePaginee", entite().uname, ";");
        f.L____________("})");
    }
}
