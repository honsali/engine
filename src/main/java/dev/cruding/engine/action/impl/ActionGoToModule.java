package dev.cruding.engine.action.impl;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.component.bouton.Actionnable;
import dev.cruding.engine.flow.ViewFlow;

public class ActionGoToModule extends Action {

    private String targetModule;


    public ActionGoToModule(String targetModule) {
        this.targetModule = targetModule;
    };

    public void actionnable(Actionnable actionnable) {
        this.actionnable = actionnable;
        this.actionnable.lcoreName("goToModule" + targetModule);
        this.actionnable.lname("goToModule" + targetModule);
    }


    public boolean addViewScript(ViewFlow f) {
        f.totalScript().L____("const goToModule", targetModule, " = (", entite().lname, ") => {");
        f.totalScript().L________("goToModule(APP_MODULES.", targetModule.toUpperCase() + ", ", entite().lname, ");");
        f.totalScript().L____("};");
        f.useGoToModule();
        return true;
    }

}
