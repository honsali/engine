package dev.cruding.engine.action.listPaginated.injection;

import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.injection.ActionViewInjection;

public class ListPaginatedViewInjection extends ActionViewInjection {

    public boolean addViewScript(ViewFlow f) {
        f.totalScript().L____("useEffect(() => {");
        f.totalScript().L________(lnameWithEntity(), "();");
        f.totalScript().L____("}, []);");
        f.useEffect();
        return true;
    }
}
