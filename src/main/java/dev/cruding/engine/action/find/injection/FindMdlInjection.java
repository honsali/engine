package dev.cruding.engine.action.find.injection;

import dev.cruding.engine.flow.MdlFlow;
import dev.cruding.engine.injection.ActionMdlInjection;

public class FindMdlInjection extends ActionMdlInjection {

    public void addMdlImport(MdlFlow f) {
        f.addMdlImport("{ I" + entity().uname + " }", "modele/" + entity().path + "/Domaine" + entity().uname);
        f.addMdlImport("{ FormInstance }", "antd");
    }

    public void addMdlRequestAttribute(MdlFlow f) {
        f.addMdlRequestAttribute("form", "FormInstance");
    }

    public void addMdlResultAttribute(MdlFlow f) {
        f.addMdlResultAttribute(entity().lname, "I" + entity().uname);
    }

    public void addMdlStateAttribute(MdlFlow f) {
        f.addMdlStateAttribute(entity().lname, "I" + entity().uname);
        f.addMdlSelectorAttribute(entity().lname, entity().uname);
    }

    public void addMdlExtraReducerAffectation(MdlFlow f) {
        f.L________________("state.", entity().lname, " = action.payload.", entity().lname, ";");
    }
}
