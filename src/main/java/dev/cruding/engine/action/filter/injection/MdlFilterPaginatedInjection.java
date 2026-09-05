package dev.cruding.engine.action.filter.injection;

import dev.cruding.engine.flow.MdlFlow;

public class MdlFilterPaginatedInjection extends MdlFilterInjection {

    public void addMdlImport(MdlFlow f) {
        f.addMdlImport("{ IListePaginee" + entity().uname + " }", "modele/" + entity().path + "/Domaine" + entity().uname);
        f.addMdlImport("{ IRequete" + entity().uname + " }", "modele/" + entity().path + "/Domaine" + entity().uname);
    }

    public void addMdlRequestAttribute(MdlFlow f) {
        super.addMdlRequestAttribute(f);
        f.addMdlRequestAttribute("pageCourante", "number");
    }

    public void addMdlResultAttribute(MdlFlow f) {
        f.addMdlResultAttribute("listePaginee" + entity().uname, "IListePaginee" + entity().uname);
        f.addMdlResultAttribute("filtre", "IRequete" + entity().uname);
    }

    public void addMdlStateAttribute(MdlFlow f) {
        f.addMdlStateAttribute("listePaginee" + entity().uname, "IListePaginee" + entity().uname);
        f.addMdlStateAttribute("filtre", "IRequete" + entity().uname);
        f.addMdlSelectorAttribute("listePaginee" + entity().uname, "ListePaginee" + entity().uname);
    }

    public void addMdlExtraReducerAffectation(MdlFlow f) {
        f.L________________("state.listePaginee", entity().uname, " = action.payload.listePaginee", entity().uname, ";");
        f.L________________("state.filtre = action.payload.filtre;");
        f.L________________("state.pageCourante = 0;");
    }
}
