package dev.cruding.engine.action.list.injection;

import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.injection.ActionViewInjection;

public class ListViewInjection extends ActionViewInjection {

    public boolean addViewScript(ViewFlow f) {

        if (!flow()) {
            f.useInitAction(action);
        }
        return false;
    }
}
