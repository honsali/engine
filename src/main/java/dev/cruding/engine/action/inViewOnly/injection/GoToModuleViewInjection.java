package dev.cruding.engine.action.inViewOnly.injection;

import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.injection.ActionViewInjection;

public class GoToModuleViewInjection extends ActionViewInjection {


    private String targetModule;


    public GoToModuleViewInjection(String targetModule) {
        this.targetModule = targetModule;
    };

    public boolean addViewScript(ViewFlow f) {
        f.totalScript().L____("const goToModule", targetModule, " = (", entity().lname, ") => {");
        f.totalScript().L________("goToModule(APP_MODULES.", targetModule.toUpperCase() + ", ", entity().lname, ");");
        f.totalScript().L____("};");
        f.useGoToModule();
        return true;
    }
}
