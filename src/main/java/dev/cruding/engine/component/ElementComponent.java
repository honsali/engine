package dev.cruding.engine.component;

import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.field.Field;
import dev.cruding.engine.gen.Page;

public abstract class ElementComponent extends Component {
    public boolean communModule = false;
    public boolean communEntite = false;

    public ElementComponent(Page page) {
        super(page);
        inElement = true;
    }

    public ElementComponent(Page page, Entity entity) {
        super(page, entity);
        inElement = true;
    }

    public ElementComponent(Page page, Entity entity, Component... componentList) {
        super(page, entity, componentList);
        for (Component component : componentList) {
            component.inElement = true;
        }
        inElement = true;
    }

    public ElementComponent(Page page, Entity entity, Field... fieldList) {
        super(page, entity, fieldList);
        inElement = true;
    }

    public ElementComponent(Page page, Component... componentList) {
        super(page, componentList);
        for (Component component : componentList) {
            component.inElement = true;
        }
        inElement = true;
    }

    public ElementComponent(Page page, Field... fieldList) {
        super(page, fieldList);
        inElement = true;
    }

    public ElementComponent communModule() {
        this.communModule = true;
        return this;
    }


    public ElementComponent communEntite() {
        this.communEntite = true;
        return this;
    }

}
