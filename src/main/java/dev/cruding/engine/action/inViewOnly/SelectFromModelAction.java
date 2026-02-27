package dev.cruding.engine.action.inViewOnly;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.action.inViewOnly.injection.SelectFromModelMdlInjection;
import dev.cruding.engine.action.inViewOnly.injection.SelectFromModelViewInjection;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entity.Entity;

public class SelectFromModelAction extends Action {

    public SelectFromModelAction(Entity entity, Element element) {
        super(ActionType.NOUI, "changerSelection", entity, element);
        inViewOnly();
    }

    public void overrideActionInjection() {
        mdlActionInjection = new SelectFromModelMdlInjection();
        viewActionInjection = new SelectFromModelViewInjection();
    }
}
