package dev.cruding.engine.action.inViewOnly.injection;

import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.injection.ViewActionInjection;

public class ViewGoToModuleInjection extends ViewActionInjection {


    private String targetModule;


    public ViewGoToModuleInjection(String targetModule) {
        this.targetModule = targetModule;
    };

    public boolean addViewScript(ViewFlow f) {
        f.totalScript().L____("const goToModule", targetModule, " = (", entite().lname, ") => {");
        f.totalScript().L________("goToModule(APP_MODULES.", targetModule.toUpperCase() + ", ", entite().lname, ");");
        f.totalScript().L____("};");
        f.useGoToModule();
        return true;
    }
}
