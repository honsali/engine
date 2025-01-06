package dev.cruding.engine.action.impl;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.flow.ViewFlow;

public class ActionRecupererEnSession extends Action {

    public String variable;


    public ActionRecupererEnSession(String variable) {
        this.variable = variable;
    }

    public boolean addViewScript(ViewFlow f) {
        if (variable != null) {
            f.addSpecificSelector(variable, mvcPath() + "/Mdl" + uc());
        } else {
            f.addSpecificSelector(entite().lname, entite().uname, mvcPath() + "/Mdl" + uc());
        }
        return false;
    }
}
