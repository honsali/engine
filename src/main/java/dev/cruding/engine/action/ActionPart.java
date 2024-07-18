package dev.cruding.engine.action;

import dev.cruding.engine.flow.ViewFlow;

public abstract class ActionPart extends ActionInViewOnly {

    public ActionPart(String actionType) {
        super(actionType);
    }

    public void addViewImport(ViewFlow flow) {}

    public void addScript(ViewFlow flow, int level) {}
}
