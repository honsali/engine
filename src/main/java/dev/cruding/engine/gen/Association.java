package dev.cruding.engine.gen;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entity.Entity;

public class Association {

    protected Entity entity;
    protected Page page;
    protected Element element;
    protected Action action;

    public Association(Page page, Action action) {
        this.page = page;
        this.action = action;
    }

    public Association(Entity entity, Page page, Action action) {
        this.entity = entity;
        this.page = page;
        this.action = action;
    }

    public Association(Entity entity, Page page, Element element, Action action) {
        this.entity = entity;
        this.page = page;
        this.element = element;
        this.action = action;
    }

}
