package dev.cruding.engine.field;

import dev.cruding.engine.flow.MCFlow;
import dev.cruding.engine.flow.ViewFlow;

public class FieldInAction {
    public void addMdlResultAttribute(MCFlow f) {}

    public void addMdlStateAttribute(MCFlow f) {}

    public void addMdlExtraReducer(MCFlow f) {}

    public void addMdlImport(MCFlow f) {}

    public void addMdlSelector(MCFlow f, String uc) {}

    public void addCtrlImport(MCFlow f) {}

    public void addCtrlImplementation(MCFlow f) {}

    public void addViewScript(ViewFlow flow, String uc, String mvcPath) {}

}
