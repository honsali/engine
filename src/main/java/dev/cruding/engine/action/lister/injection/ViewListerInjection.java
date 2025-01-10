package dev.cruding.engine.action.lister.injection;

import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.injection.ViewActionInjection;

public class ViewListerInjection extends ViewActionInjection {

    public boolean addViewScript(ViewFlow f) {
        f.totalScript().L____("useEffect(() => {");
        f.totalScript().L________("execute(Ctrl", uc(), ".", lname());
        if (byProp()) {
            f.totalScript().append(", { ").append(element().byProp).append(" }");
        }
        f.totalScript().append(");");
        f.totalScript().L____("}, [");
        if (byProp()) {
            f.totalScript().append(element().byProp);
        }
        f.totalScript().append("]);");
        f.useExecute();
        f.useEffect();
        f.addJsImport("Ctrl" + uc(), mvcPath() + "/Ctrl" + uc());
        return true;
    }
}
