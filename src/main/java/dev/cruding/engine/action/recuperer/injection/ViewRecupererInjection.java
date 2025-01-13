package dev.cruding.engine.action.recuperer.injection;

import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.injection.ViewActionInjection;

public class ViewRecupererInjection extends ViewActionInjection {



    public boolean addViewScript(ViewFlow f) {
        f.totalScript().L____("useEffect(() => {");
        f.totalScript().L________(f.getPretCondition(), "execute(Ctrl", uc(), ".", lnameAvecEntite(), ");");
        f.totalScript().L____("}, [", f.getPretArray(), "]);");
        f.useExecute();
        f.useEffect();
        f.addJsImport("Ctrl" + uc(), mvcPath() + "/Ctrl" + uc());
        return true;
    }

    public void addViewSelector(ViewFlow f) {
        f.addSpecificSelector(entite().uname, mvcPath() + "/Mdl" + uc());
    }

}
