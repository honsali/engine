package dev.cruding.engine.component;

import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.field.Field;
import dev.cruding.engine.flow.Flow;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.Context;
import dev.cruding.engine.gen.Page;

public abstract class Component {
    public static final String tab = "    ";
    public static final String[] indent = new String[] {"\n" + tab, "\n" + tab + tab, "\n" + tab + tab + tab, "\n" + tab + tab + tab + tab, "\n" + tab + tab + tab + tab + tab, "\n" + tab + tab + tab + tab + tab + tab};

    public Component[] componentList;
    public Field[] fieldList;
    public Entity entity;
    public Page page;
    public Component fatherComponent;
    public boolean inElement = false;

    public Component(Page page) {
        this.page = page;
        linkToView();
    }

    public Component(Page page, Entity entity) {
        this.page = page;
        this.entity = entity;
        linkToView();
    }

    public Component(Page page, Entity entity, Component... componentList) {
        this(page, entity);
        this.componentList = componentList;
    }

    public Component(Page page, Entity entity, Field... fieldList) {
        this(page, entity);
        this.fieldList = fieldList;
    }

    public Component(Page page, Component... componentList) {
        this(page);
        this.componentList = componentList;
    }

    public Component(Page page, Field... fieldList) {
        this(page);
        this.fieldList = fieldList;
    }

    public void addLabel(String key, String value) {
        Context.getInstance().addLabel(key, value);
    }

    public void addInlineContent(ViewFlow flow) {
        addImport(flow);
        addScript(flow);
        addInlineTag(flow);
    }

    public void addContent(Component fatherComponent, ViewFlow flow, int level) {
        inElement = inElement || (fatherComponent != null && fatherComponent.inElement);
        this.fatherComponent = fatherComponent;
        addImport(flow);
        addScript(flow);
        addOpenTag(flow, level);
        if (componentList != null) {
            for (Component component : componentList) {
                component.addContent(this, flow, level + 1);
            }
        }
        addCloseTag(flow, level);
        if (level == 1) {
            flow.flush(this);
        }
    }

    public String getFirstChildTitle() {
        return componentList[0].getTitle();
    }

    public String getTitle() {
        return "";
    }

    public void addI18n(Flow flow, Page page) {}

    public void addActionModule(Flow flow, Page page) {}

    public void addImport(ViewFlow flow) {}

    public void addOpenTag(ViewFlow c, int level) {}

    public void addCloseTag(ViewFlow c, int level) {}

    public void addInlineTag(Flow c) {}

    public void addScript(ViewFlow c) {}

    public void linkToView() {}

}
