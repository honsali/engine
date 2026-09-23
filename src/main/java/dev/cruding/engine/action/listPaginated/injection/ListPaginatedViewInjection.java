package dev.cruding.engine.action.listPaginated.injection;

import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.injection.ActionViewInjection;

public class ListPaginatedViewInjection extends ActionViewInjection {

    public boolean addViewScript(ViewFlow f) {
        if (!flow()) {
            f.useInitAction(action);
        }
        return false;
    }
}
