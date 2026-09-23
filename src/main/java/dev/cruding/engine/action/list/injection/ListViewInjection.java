package dev.cruding.engine.action.list.injection;

import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.injection.ActionViewInjection;

public class ListViewInjection extends ActionViewInjection {

    public boolean addViewScript(ViewFlow f) {

        f.addSelector(lnameWithEntity());
        f.useEffect();
        f.totalScript().L____("useEffect(() => {");
        f.totalScript().L________(lnameWithEntity(), "(");
        if (byProp() != null) {
            f.totalScript().append("{ ").append(byProp()).append(" }");
        }
        f.totalScript().append(");");
        f.totalScript().L____("}, [");
        if (byProp() != null) {
            f.totalScript().append(byProp());
        }
        f.totalScript().append("]);");
        return true;
    }
}
