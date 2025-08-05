package dev.cruding.engine.action.lister.injection;

import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.injection.ViewActionInjection;

public class ViewListerInjection extends ViewActionInjection {

    public boolean addViewScript(ViewFlow f) {

        f.addSelector(lnameAvecEntite());
        f.useEffect();
        f.totalScript().L____("useEffect(() => {");
        f.totalScript().L________(lnameAvecEntite(), "(");
        if (parProp() != null) {
            f.totalScript().append("{ ").append(parProp()).append(" }");
        }
        f.totalScript().append(");");
        f.totalScript().L____("}, [");
        if (parProp() != null) {
            f.totalScript().append(parProp());
        }
        f.totalScript().append("]);");
        return true;
    }
}
