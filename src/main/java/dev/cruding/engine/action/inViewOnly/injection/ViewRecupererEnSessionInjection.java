package dev.cruding.engine.action.inViewOnly.injection;

import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.injection.ViewActionInjection;

public class ViewRecupererEnSessionInjection extends ViewActionInjection {

    public String variable;

    public ViewRecupererEnSessionInjection(String variable) {
        this.variable = variable;
    }

    public boolean addViewScript(ViewFlow f) {
        f.addSelector(variable);
        return false;
    }
}
