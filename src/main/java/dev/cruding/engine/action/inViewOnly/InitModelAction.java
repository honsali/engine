package dev.cruding.engine.action.inViewOnly;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.action.inViewOnly.injection.InitModelMdlInjection;
import dev.cruding.engine.action.inViewOnly.injection.InitModelViewInjection;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entity.Entity;

public class InitModelAction extends Action {

    public InitModelAction(Entity entity, Element element) {
        super(ActionType.NORMAL, "initialiser", entity, element);
        inViewOnly();
    }

    public void overrideActionInjection() {
        viewActionInjection = new InitModelViewInjection();
        mdlActionInjection = new InitModelMdlInjection();
    }
}
