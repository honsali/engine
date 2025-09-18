package dev.cruding.engine.action.creer.injection;

import dev.cruding.engine.flow.MdlFlow;
import dev.cruding.engine.injection.MdlActionInjection;

public class MdlCreerInjection extends MdlActionInjection {

    public void addMdlImport(MdlFlow f) {
        f.addMdlImport("{ FormInstance }", "antd");
    }

    public void addMdlRequestAttribute(MdlFlow f) {
        f.addMdlRequestAttribute("form", "FormInstance");
        if (parIdPere() && entite().havePere) {
            f.addMdlRequestAttribute("id" + entite().upere, "string");
        }
    }

    public void addMdlResultAttribute(MdlFlow f) {
        f.addMdlResultAttribute("id" + entite().uname, "string");

    }

    public void addMdlStateAttribute(MdlFlow f) {
        f.addMdlStateAttribute("id" + entite().uname, "string");
        f.addMdlSelectorAttribute("id" + entite().uname, "Id" + entite().uname);
    }


    public void addMdlExtraReducerAffectation(MdlFlow f) {
        f.L________________("state.id", entite().uname, " = action.payload.id", entite().uname, ";");
    }
}
