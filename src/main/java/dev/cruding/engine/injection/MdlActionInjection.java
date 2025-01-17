package dev.cruding.engine.injection;

import dev.cruding.engine.action.ActionWrapper;
import dev.cruding.engine.flow.MdlFlow;

public class MdlActionInjection extends ActionWrapper {

    public void addMdlImport(MdlFlow f) {}

    public void addMdlRequestAttribute(MdlFlow f) {}

    public void addMdlResultAttribute(MdlFlow f) {}

    public void addMdlStateAttribute(MdlFlow f) {}

    public void addMdlSelector(MdlFlow f) {}

    public void addMdlExtraReducer(MdlFlow f) {}

    public boolean addMdlReducer(MdlFlow f) {
        return false;
    }
}
