package dev.cruding.engine.action.inViewOnly;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.action.inViewOnly.injection.InitFilterViewInjection;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entity.Entity;

public class InitFilterAction extends Action {

    public Action action;

    public InitFilterAction(Entity entity, Element element, Action action) {
        super(ActionType.UCA, "initialiserFiltre", entity, element);
        this.action = action;
        inViewOnly();
    }

    public void overrideActionInjection() {
        viewActionInjection = new InitFilterViewInjection(action);
    }
}
