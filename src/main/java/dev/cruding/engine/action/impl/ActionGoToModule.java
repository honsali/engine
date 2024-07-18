package dev.cruding.engine.action.impl;

import dev.cruding.engine.action.ActionInViewOnly;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.Page;

public class ActionGoToModule extends ActionInViewOnly {

    private String targetModule;

    public ActionGoToModule(Page page, String targetModule) {
        super("goTo");
        this.targetModule = targetModule;
    }

    public ActionGoToModule entity(Entity entity) {
        super.entity(entity);
        this.lname = "goTo" + targetModule;
        return this;
    }

    public void addViewScript(ViewFlow f) {

        f.totalScript().L("");
        f.totalScript().L____("const goTo", targetModule, " = (", entity.lname, ") => {");
        f.totalScript().L________("goToModule(APP_MODULES.", targetModule.toUpperCase() + ", ", entity.lname, ");");
        f.totalScript().L____("};");
        f.useGoToModule();

    }

}
