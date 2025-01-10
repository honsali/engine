package dev.cruding.engine.action.listerEnPage.injection;

import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.injection.ViewActionInjection;

public class ViewListerEnPageInjection extends ViewActionInjection {

    public boolean addViewScript(ViewFlow f) {
        f.totalScript().L____("useEffect(() => {");
        f.totalScript().L________("execute(Ctrl", uc(), ".", lname(), ");");
        f.totalScript().L____("}, []);");
        f.useExecute();
        f.useEffect();
        f.addJsImport("Ctrl" + uc(), mvcPath() + "/Ctrl" + uc());
        return true;
    }
}
