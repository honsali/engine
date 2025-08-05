package dev.cruding.engine.action.filtrer.injection;

import dev.cruding.engine.flow.MdlFlow;
import dev.cruding.engine.injection.MdlActionInjection;

public class MdlFiltrerInjection extends MdlActionInjection {

    public void addMdlImport(MdlFlow f) {
        f.addMdlImport("{ I" + entite().uname + " }", "modele/" + entite().path + "/Domaine" + entite().uname);
        f.addMdlImport("{ IRequete" + entite().uname + " }", "modele/" + entite().path + "/Domaine" + entite().uname);
        f.addMdlImport("{ FormInstance }", "antd");
    }

    public void addMdlRequestAttribute(MdlFlow f) {
        f.addMdlRequestAttribute("form", "FormInstance");
    }

    public void addMdlResultAttribute(MdlFlow f) {
        f.addMdlResultAttribute("liste" + entite().uname, "I" + entite().uname + "[]");
        f.addMdlResultAttribute("filtre", "IRequete" + entite().uname);
    }

    public void addMdlStateAttribute(MdlFlow f) {
        f.addMdlStateAttribute("liste" + entite().uname, "I" + entite().uname + "[]");
        f.addMdlStateAttribute("filtre", "IRequete" + entite().uname);
        f.addMdlSelectorAttribute("liste" + entite().uname, "Liste" + entite().uname);
    }

    public void addMdlExtraReducerAffectation(MdlFlow f) {
        f.L________________("state.liste", entite().uname, " = action.payload.liste", entite().uname, ";");
        f.L________________("state.filtre = action.payload.filtre;");
    }
}
