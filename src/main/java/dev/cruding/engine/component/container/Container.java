package dev.cruding.engine.component.container;

import org.apache.commons.lang3.StringUtils;
import dev.cruding.engine.component.Component;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.Context;

public class Container<T extends Container<T>> extends Component {

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

    @SuppressWarnings("unchecked")
    private T self() {
        return (T) this;
    }

    protected String titleAttribute(String title) {
        if (title != null) {
            String label = StringUtils.capitalize(StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(title), " "));
            Context.getInstance().addLabel(element.page.module.uname, "Uc" + element.page.uc + "." + title, label);
        }
        return title != null ? (" titre=\"" + title + "\"") : "";
    }

}
