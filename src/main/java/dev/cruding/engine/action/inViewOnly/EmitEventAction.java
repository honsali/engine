package dev.cruding.engine.action.inViewOnly;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.action.inViewOnly.injection.EmitEventViewInjection;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entity.Entity;

public class EmitEventAction extends Action {


    private String targetEvent;

    public EmitEventAction(Entity entity, Element element, String targetEvent) {
        super(ActionType.NOUI, targetEvent, entity, element);
        this.targetEvent = targetEvent;
        inViewOnly();
    }

    public void overrideActionInjection() {
        viewActionInjection = new EmitEventViewInjection(targetEvent);
    }
}
