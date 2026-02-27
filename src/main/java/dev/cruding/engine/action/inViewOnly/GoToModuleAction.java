package dev.cruding.engine.action.inViewOnly;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.action.inViewOnly.injection.GoToModuleViewInjection;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entity.Entity;

public class GoToModuleAction extends Action {

    private String targetModule;

    public GoToModuleAction(Entity entity, Element element, String targetModule) {
        super(ActionType.NOUI, "goToModule", entity, element);
        this.targetModule = targetModule;
        inViewOnly();
        lcoreName("goToModule");
        lnameWithoutEntity("goToModule" + targetModule);
        lnameWithEntity("goToModule" + targetModule);
    }

    public void overrideActionInjection() {
        viewActionInjection = new GoToModuleViewInjection(targetModule);
    }
}
