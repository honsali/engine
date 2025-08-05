package dev.cruding.engine.action.listerEnPage.injection;

import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.injection.ViewActionInjection;

public class ViewListerEnPageInjection extends ViewActionInjection {

    public boolean addViewScript(ViewFlow f) {
        f.totalScript().L____("useEffect(() => {");
        f.totalScript().L________(lnameAvecEntite(), "();");
        f.totalScript().L____("}, []);");
        f.useEffect();
        return true;
    }
}
