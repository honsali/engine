package dev.cruding.engine.action.inViewOnly;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entity.Entity;

public class EmptyAction extends Action {

    public EmptyAction(ActionType type, String lcoreName, Entity entity, Element element) {
        super(type, lcoreName, entity, element);
        this.isEmpty = true;
        inViewOnly();
    }


}
