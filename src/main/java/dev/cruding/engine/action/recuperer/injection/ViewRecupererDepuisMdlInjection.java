package dev.cruding.engine.action.recuperer.injection;

import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.injection.ViewActionInjection;

public class ViewRecupererDepuisMdlInjection extends ViewActionInjection {

    public boolean addViewScript(ViewFlow f) {
        f.totalScript().L____("useEffect(() => {");
        if (attendreSiPret()) {
            f.totalScript().L________("if (pret) {");
            f.totalScript().L____________(lnameAvecEntite(), "();");
            f.totalScript().L________("}");
            f.totalScript().L____("}, [pret]);");
        } else {
            f.totalScript().L________(lnameAvecEntite(), "();");
            f.totalScript().L____("}, []);");
        }
        f.useEffect();
        // f.addSelector(entite().lname);
        f.addSelector(lnameAvecEntite());
        return true;
    }

    public void addViewSelector(ViewFlow f) {
        f.addSelector(entite().uname);
    }

}
