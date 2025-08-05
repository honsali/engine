package dev.cruding.engine.action.filtrer.injection;

import dev.cruding.engine.flow.MdlFlow;
import dev.cruding.engine.injection.MdlActionInjection;

public class MdlFiltrerPagineInjection extends MdlActionInjection {

    public void addMdlImport(MdlFlow f) {
        f.addMdlImport("{ IListePaginee" + entite().uname + " }", "modele/" + entite().path + "/Domaine" + entite().uname);
        f.addMdlImport("{ IRequete" + entite().uname + " }", "modele/" + entite().path + "/Domaine" + entite().uname);
        f.addMdlImport("{ FormInstance }", "antd");
    }

    public void addMdlRequestAttribute(MdlFlow f) {
        f.addMdlRequestAttribute("form", "FormInstance");
        f.addMdlRequestAttribute("pageCourante", "number");
    }

    public void addMdlResultAttribute(MdlFlow f) {
        f.addMdlResultAttribute("listePaginee" + entite().uname, "IListePaginee" + entite().uname);
        f.addMdlResultAttribute("filtre", "IRequete" + entite().uname);
    }

    public void addMdlStateAttribute(MdlFlow f) {
        f.addMdlStateAttribute("listePaginee" + entite().uname, "IListePaginee" + entite().uname);
        f.addMdlStateAttribute("filtre", "IRequete" + entite().uname);
        f.addMdlSelectorAttribute("listePaginee" + entite().uname, "ListePaginee" + entite().uname);
    }

    public void addMdlExtraReducerAffectation(MdlFlow f) {
        f.L________________("state.listePaginee", entite().uname, " = action.payload.listePaginee", entite().uname, ";");
        f.L________________("state.filtre = action.payload.filtre;");
        f.L________________("state.pageCourante = 0;");
    }
}
