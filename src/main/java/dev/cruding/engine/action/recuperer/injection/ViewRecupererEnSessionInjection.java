package dev.cruding.engine.action.recuperer.injection;

import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.injection.ViewActionInjection;

public class ViewRecupererEnSessionInjection extends ViewActionInjection {

    public String variable;

    public ViewRecupererEnSessionInjection(String variable) {
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
