package dev.cruding.engine.action.chercher.injection;

import dev.cruding.engine.flow.MdlFlow;
import dev.cruding.engine.injection.MdlActionInjection;

public class MdlChercherInjection extends MdlActionInjection {

    public void addMdlImport(MdlFlow f) {
        f.addMdlImport("{ IListePaginee" + entite().uname + " }", "modele/" + entite().path + "/Domaine" + entite().uname);
        f.addMdlImport("{ IRequete" + entite().uname + " }", "modele/" + entite().path + "/Domaine" + entite().uname);
    }

    public void addMdlRequestAttribute(MdlFlow f) {
        f.addMdlRequestAttribute("form", "any");
        f.addMdlRequestAttribute("pageCourante", "number");
    }

    public void addMdlResultAttribute(MdlFlow f) {
        f.addMdlResultAttribute("listePaginee" + entite().uname, "IListePaginee" + entite().uname);
        f.addMdlResultAttribute("filtre", "IRequete" + entite().uname);
    }

    public void addMdlStateAttribute(MdlFlow f) {
        f.addMdlStateAttribute("listePaginee" + entite().uname, "IListePaginee" + entite().uname);
        f.addMdlStateAttribute("filtre", "IRequete" + entite().uname);
    }

    public void addMdlSelector(MdlFlow f) {
        f.L("export const selectListePaginee", entite().uname, " = createSelector([selectMdl", uc(), "], (state: ", uc(), "Type) => state.listePaginee", entite().uname, ");");
    }

    public void addMdlExtraReducer(MdlFlow f) {
        f.L____________(".addCase(Ctrl", uc(), ".", lnameAvecEntite(), ".fulfilled, (state, action) => {");
        f.L________________("state.resultat = action.payload;");
        f.L________________("state.listePaginee", entite().uname, " = action.payload.listePaginee", entite().uname, ";");
        f.L________________("state.filtre = action.payload.filtre;");
        f.L____________("})");
    }
}
