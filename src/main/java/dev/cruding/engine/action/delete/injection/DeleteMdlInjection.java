package dev.cruding.engine.action.delete.injection;

import dev.cruding.engine.flow.MdlFlow;
import dev.cruding.engine.injection.ActionMdlInjection;

public class DeleteMdlInjection extends ActionMdlInjection {


    public void addMdlImport(MdlFlow f) {}

    public void addMdlRequestAttribute(MdlFlow f) {

        if (byFatherId() && entity().haveFather) {
            f.addMdlRequestAttribute("id" + entity().ufather, "string");
        }
        f.addMdlRequestAttribute("id" + entity().uname, "string");
    }

    public void addMdlResultAttribute(MdlFlow f) {}

    public void addMdlStateAttribute(MdlFlow f) {

    }

    public void addMdlExtraReducerAffectation(MdlFlow f) {}

}
