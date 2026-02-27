package dev.cruding.engine.action.changePage.injection;

import dev.cruding.engine.flow.MdlFlow;
import dev.cruding.engine.injection.ActionMdlInjection;

public class ChangePageMdlInjection extends ActionMdlInjection {


    public void addMdlImport(MdlFlow f) {
        f.addMdlImport("{ IListePaginee" + entity().uname + " }", "modele/" + entity().path + "/Domaine" + entity().uname);
    }

    public void addMdlRequestAttribute(MdlFlow f) {
        f.addMdlRequestAttribute("pageCourante", "number");
    }

    public void addMdlResultAttribute(MdlFlow f) {
        f.addMdlResultAttribute("listePaginee" + entity().uname, "IListePaginee" + entity().uname);
    }

    public void addMdlStateAttribute(MdlFlow f) {
        f.addMdlStateAttribute("listePaginee" + entity().uname, "IListePaginee" + entity().uname);
        f.addMdlStateAttribute("pageCourante", "number");
        f.addMdlSelectorAttribute("listePaginee" + entity().uname, "ListePaginee" + entity().uname);
    }

    public void addMdlExtraReducerAffectation(MdlFlow f) {
        f.L________________("state.listePaginee", entity().uname, " = action.payload.listePaginee", entity().uname, ";");
        f.L________________("state.pageCourante = action.payload.listePaginee", entity().uname, ".pagination.pageCourante;");
    }
}
