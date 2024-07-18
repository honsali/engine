package dev.cruding.engine.action.impl;

import dev.cruding.engine.action.ActionInViewOnly;
import dev.cruding.engine.flow.ViewFlow;

public class ActionRecupererEnSession extends ActionInViewOnly {

    public ActionRecupererEnSession() {
        super("recupererEnSession");
    }

    public void addViewScript(ViewFlow f) {
        f.addSpecificSelector(entity.lname, entity.uname, mvcPath + "/Mdl" + uc);
    }
}