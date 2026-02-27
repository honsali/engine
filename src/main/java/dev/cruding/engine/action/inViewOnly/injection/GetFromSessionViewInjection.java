package dev.cruding.engine.action.inViewOnly.injection;

import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.injection.ActionViewInjection;

public class GetFromSessionViewInjection extends ActionViewInjection {

    public String variable;

    public GetFromSessionViewInjection(String variable) {
        this.variable = variable;
    }

    public boolean addViewScript(ViewFlow f) {
        f.addSelector(variable);
        return false;
    }
}
