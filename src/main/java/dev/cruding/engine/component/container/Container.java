package dev.cruding.engine.component.container;

import org.apache.commons.lang3.StringUtils;
import dev.cruding.engine.component.Component;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.Context;

public class Container<T extends Container<T>> extends Component {

    public String title = null;
    public String width = null;
    public String margin = null;
    public String background = null;

    public Container(Element element, Component... componentList) {
        super(element, componentList);
    }

    public Container(Element element, Entity entity, Component... componentList) {
        super(element, entity, componentList);
    }

    public void addImport(ViewFlow flow) {
        for (Component component : componentList) {
            component.addImport(flow);
        }
    }

    public T content(Component... componentList) {
        this.componentList = clean(componentList);
        return self();
    }

    @Override
    public T name(String name) {
        this.name = name;
        return self();
    }

    public T title(String title) {
        this.title = title;
        return self();

    }

    public T background(String background) {
        this.background = background;
        return self();
    }

    public T margin(String margin) {
        this.margin = margin;
        return self();
    }

    public T width(String width) {
        this.width = width;
        return self();
    }

    @SuppressWarnings("unchecked")
    private T self() {
        return (T) this;
    }

    public String title() {
        if (title != null) {
            String label = StringUtils.capitalize(StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(title), " "));
            Context.getInstance().addLabel(element.page.module.uname, "Uc" + element.page.uc + "." + title, label);
        }
        return title != null ? (" titre=\"" + title + "\"") : "";
    }

}
