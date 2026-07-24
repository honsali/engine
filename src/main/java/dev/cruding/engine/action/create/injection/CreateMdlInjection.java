package dev.cruding.engine.action.create.injection;

import dev.cruding.engine.flow.MdlFlow;
import dev.cruding.engine.injection.ActionMdlInjection;

public class CreateMdlInjection extends ActionMdlInjection {

    public void addMdlImport(MdlFlow f) {
        f.addMdlImport("{ FormInstance }", "antd");
    }

    public void addMdlRequestAttribute(MdlFlow f) {
        f.addMdlRequestAttribute("form", "FormInstance");
        if (byFatherId() && entity().haveFather) {
            f.addMdlRequiredRequestAttribute("id" + entity().ufather, "string");
        }
    }

    public void addMdlResultAttribute(MdlFlow f) {
        f.addMdlResultAttribute("id" + entity().uname, "string");

    }

    public void addMdlStateAttribute(MdlFlow f) {
        f.addMdlStateAttribute("id" + entity().uname, "string");
        f.addMdlSelectorAttribute("id" + entity().uname, "Id" + entity().uname);
    }


    public void addMdlExtraReducerAffectation(MdlFlow f) {
        f.L________________("state.id", entity().uname, " = action.payload.id", entity().uname, ";");
    }
}
