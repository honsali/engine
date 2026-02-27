package dev.cruding.engine.action.listPaginated.injection;

import dev.cruding.engine.flow.MdlFlow;
import dev.cruding.engine.injection.ActionMdlInjection;

public class ListPaginatedMdlInjection extends ActionMdlInjection {

    public void addMdlImport(MdlFlow f) {
        f.addMdlImport("{ IListePaginee" + entity().uname + " }", "modele/" + entity().path + "/Domaine" + entity().uname);
    }

    public void addMdlRequestAttribute(MdlFlow f) {
        f.addMdlRequestAttribute("pageCourante", "number");

        if (byFatherId() && entity().haveFather) {
            f.addMdlRequestAttribute("id" + entity().ufather, "string");
        }
    }

    public void addMdlResultAttribute(MdlFlow f) {
        f.addMdlResultAttribute("listePaginee" + entity().uname, "IListePaginee" + entity().uname);
    }

    public void addMdlStateAttribute(MdlFlow f) {
        f.addMdlStateAttribute("listePaginee" + entity().uname, "IListePaginee" + entity().uname);
        f.addMdlSelectorAttribute("listePaginee" + entity().uname, "ListePaginee" + entity().uname);
    }

    public void addUseSelector(MdlFlow f) {
        f.L________("listePaginee", entity().uname, ",");
    }

    public void addMdlExtraReducerAffectation(MdlFlow f) {
        f.L________________("state.listePaginee", entity().uname, " = action.payload.listePaginee", entity().uname, ";");
    }
}
