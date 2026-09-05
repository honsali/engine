package dev.cruding.engine.action.filter.injection;

import dev.cruding.engine.flow.MdlFlow;
import dev.cruding.engine.injection.ActionMdlInjection;

public class MdlFilterInjection extends ActionMdlInjection {

    public void addMdlImport(MdlFlow f) {
        f.addMdlImport("{ I" + entity().uname + " }", "modele/" + entity().path + "/Domaine" + entity().uname);
        f.addMdlImport("{ IRequete" + entity().uname + " }", "modele/" + entity().path + "/Domaine" + entity().uname);
    }

    public void addMdlRequestAttribute(MdlFlow f) {
        f.addMdlRequiredRequestAttribute("filtre", "IRequete" + entity().uname);
    }

    @Override
    protected String formRequestAttribute() {
        return "filtre";
    }

    @Override
    protected String formRequestType() {
        return "IRequete" + entity().uname;
    }

    @Override
    protected boolean validatesFormInHook() {
        return false;
    }

    public void addMdlResultAttribute(MdlFlow f) {
        f.addMdlResultAttribute("liste" + entity().uname, "I" + entity().uname + "[]");
        f.addMdlResultAttribute("filtre", "IRequete" + entity().uname);
    }

    public void addMdlStateAttribute(MdlFlow f) {
        f.addMdlStateAttribute("liste" + entity().uname, "I" + entity().uname + "[]");
        f.addMdlStateAttribute("filtre", "IRequete" + entity().uname);
        f.addMdlSelectorAttribute("liste" + entity().uname, "Liste" + entity().uname);
    }

    public void addMdlExtraReducerAffectation(MdlFlow f) {
        f.L________________("state.liste", entity().uname, " = action.payload.liste", entity().uname, ";");
        f.L________________("state.filtre = action.payload.filtre;");
    }
}
