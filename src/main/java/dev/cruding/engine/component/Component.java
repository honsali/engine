package dev.cruding.engine.component;

import java.util.Arrays;
import java.util.Objects;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.field.Field;
import dev.cruding.engine.flow.Flow;
import dev.cruding.engine.flow.ViewFlow;

public abstract class Component {
    public static final String tab = "    ";
    public static final String[] indent = new String[] {//
            "\n" + tab, //
            "\n" + tab + tab, "\n" + tab + tab + tab, //
            "\n" + tab + tab + tab + tab, //
            "\n" + tab + tab + tab + tab + tab, //
            "\n" + tab + tab + tab + tab + tab + tab, //
            "\n" + tab + tab + tab + tab + tab + tab + tab, //
            "\n" + tab + tab + tab + tab + tab + tab + tab + tab, //
            "\n" + tab + tab + tab + tab + tab + tab + tab + tab + tab//
    };

    public Component[] componentList;
    public Field[] fieldList;
    public Entity entity;
    public Element element;
    public Component fatherComponent;
    public boolean inElement = false;
    public boolean isElement = false;
    public boolean inline = false;
    public String name;

    public Component(Element element) {
        this.element = element;
    }

    public Component(Element element, Entity entity) {
        this.element = element;
        this.entity = entity;
    }

    public Component(Element element, Entity entity, Component... componentList) {
        this(element, entity);
        this.componentList = componentList;
    }

    public Component(Element element, Entity entity, Field... fieldList) {
        this(element, entity);
        this.fieldList = clean(fieldList);
    }

    public Component(Element element, Component... componentList) {
        this(element);
        this.componentList = componentList;
    }

    public Component name(String name) {
        this.name = name;
        return this;
    }

    public void addLabel(String key, String value) {
        // Context.getInstance().addLabel(element.module.uname, key, value);
    }

    public void appendContent(ViewFlow vf, Flow flow) {}

    public void addContent(Component fatherComponent, ViewFlow flow, int level) {
        addContent(fatherComponent, flow, false, level);
    }

    public void addContent(Component fatherComponent, ViewFlow flow, boolean inline, int level) {
        inElement = inElement || (fatherComponent != null && fatherComponent.inElement);
        this.fatherComponent = fatherComponent;
        this.inline = inline;
        addImport(flow);
        addScript(flow);
        if (level == 1) {
            flow.totalUi().__("(");
        }
        boolean childInline = addOpenTag(flow, level);
        if (!isElement) {
            if (componentList != null) {
                for (Component component : componentList) {
                    component.addContent(this, flow, childInline, level + 1);
                }
            }
        }
        addCloseTag(flow, level);
        if (level == 1) {
            indent(flow, 0).append(");");
        }
    }

    public Flow indent(ViewFlow flow, int level) {
        if (inline) {
            return flow.totalUi().__("");
        }
        return flow.totalUi().__(indent[level]);
    }

    public void addImport(ViewFlow flow) {}

    public boolean addOpenTag(ViewFlow c, int level) {
        return false;
    }

    public void addCloseTag(ViewFlow c, int level) {}


    public void addScript(ViewFlow c) {}

    private Field[] clean(Field[] fieldList) {
        return Arrays.stream(fieldList).filter(Objects::nonNull).toArray(Field[]::new);
    }

}
