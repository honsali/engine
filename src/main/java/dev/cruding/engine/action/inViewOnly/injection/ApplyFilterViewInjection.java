package dev.cruding.engine.action.inViewOnly.injection;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.injection.ActionViewInjection;

public class ApplyFilterViewInjection extends ActionViewInjection {


    private Action action;

    public ApplyFilterViewInjection(Action action) {
        this.action = action;

    }

    public boolean addViewScript(ViewFlow f) {
        f.totalScript().L____("const appliquerFiltre", entity().uname, " = () => {");
        f.totalScript().L________(action.lnameWithEntity, "({ form });");
        f.totalScript().L____("};");
        f.addSelector(action.lnameWithEntity);
        return true;
    }

}
