package dev.cruding.engine.action.get.injection;

import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.injection.ActionViewInjection;

public class GetFromModelViewInjection extends ActionViewInjection {

    public String variable;

    public GetFromModelViewInjection(String variable) {
        this.variable = variable;
    }

    public boolean addViewScript(ViewFlow f) {
        f.useInitAction(action);
        f.addSelector(variable);
        return false;
    }

    public void addViewSelector(ViewFlow f) {
        f.addSelector(variable);
    }

}
