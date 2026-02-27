package dev.cruding.engine.action.inViewOnly;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.action.inViewOnly.injection.ApplyFilterViewInjection;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entity.Entity;

public class ApplyFilterAction extends Action {

    public Action action;

    public ApplyFilterAction(Entity entity, Element element, Action action) {
        super(ActionType.UCA, "appliquerFiltre", entity, element);
        this.action = action;
        inViewOnly();
    }


    public void overrideActionInjection() {
        viewActionInjection = new ApplyFilterViewInjection(action);
    }

}
