package dev.cruding.engine.gen;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.entity.Entity;

public class Association {

    protected Entity entity;
    protected Element element;
    protected Action action;

    public Association(Element element, Action action) {
        this.element = element;
        this.action = action;
    }

    public Association(Entity entity, Element element, Action action) {
        this.entity = entity;
        this.element = element;
        this.action = action;
    }

}
