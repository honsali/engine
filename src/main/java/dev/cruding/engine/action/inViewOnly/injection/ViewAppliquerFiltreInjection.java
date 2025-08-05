package dev.cruding.engine.action.inViewOnly.injection;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.injection.ViewActionInjection;

public class ViewAppliquerFiltreInjection extends ViewActionInjection {


    private Action action;

    public ViewAppliquerFiltreInjection(Action action) {
        this.action = action;

    }

    public boolean addViewScript(ViewFlow f) {
        f.totalScript().L____("const appliquerFiltre", entite().uname, " = () => {");
        f.totalScript().L________(action.lnameAvecEntite, "({ form });");
        f.totalScript().L____("};");
        f.addSelector(action.lnameAvecEntite);
        return true;
    }

}
