
package dev.cruding.engine.element;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Strings;
import dev.cruding.engine.component.Component;
import dev.cruding.engine.component.entity.Form;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.field.Field;
import dev.cruding.engine.field.impl.Hidden;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.Page;
import dev.cruding.engine.gen.Context;

public class Element {

    public static final Comparator<Element> ORDER_BY_PATH = Element::compareByPath;

    public static final String DETAIL = "DETAIL";
    public static final String FORM = "FORM";
    public static final String TABLE = "TABLE";

    public String path;
    public String relativePath;
    public String name;
    public Page page;
    public Component rootComponent;
    public boolean byForm = false;
    public boolean byEntity = false;
    public String byProp = null;
    public boolean fake = false;


    public Element(String name, String relativePath) {
        this.name = name;
        this.relativePath = relativePath;
    }

    public Element name(String name) {
        this.name = name;
        return this;
    }

    public Element page(Page page) {
        if (Strings.CS.startsWith(relativePath, "../")) {
            this.path = StringUtils.substringBeforeLast(page.path, "/") + "/" + StringUtils.substringAfter(relativePath, "/");
        } else if (page == null) {
            this.path = relativePath;
        } else {
            this.path = page.path + relativePath;
        }
        this.page = page;
        return this;
    }

    public Context context() {
        if (page == null) {
            throw new IllegalStateException("Element is not attached to a Page: " + name);
        }
        return page.context();
    }

    public void setRootComponent(Component component) {
        this.rootComponent = component;
    }

    public Element byForm() {
        this.byForm = true;
        return this;
    }

    public Element byEntity() {
        this.byEntity = true;
        return this;
    }

    public Element byProp(String byProp) {
        this.byProp = byProp;
        return this;
    }

    public void addContent(ViewFlow flow) {
        if (rootComponent == null) {
            return;
        }
        rootComponent.addContent(null, flow, 1);
    }

    public Optional<List<Field>> formFields(Entity entity) {
        Map<String, Field> selectedFields = new LinkedHashMap<>();
        boolean formFound = collectFormFields(rootComponent, entity, selectedFields);
        return formFound ? Optional.of(List.copyOf(selectedFields.values())) : Optional.empty();
    }

    private boolean collectFormFields(
            Component component,
            Entity entity,
            Map<String, Field> selectedFields) {
        if (component == null) {
            return false;
        }

        boolean formFound = false;
        if (component instanceof Form form && form.entity == entity) {
            formFound = true;
            for (Field field : form.fieldList) {
                boolean technicalId = field instanceof Hidden && field.lname.equals(entity.id_.lname);
                if (!field.readOnly && !field.isFather && !technicalId) {
                    selectedFields.putIfAbsent(field.lname, field);
                }
            }
        }
        if (component.componentList != null) {
            for (Component child : component.componentList) {
                formFound = collectFormFields(child, entity, selectedFields) || formFound;
            }
        }
        return formFound;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Element e)) {
            return false;
        }
        return Objects.equals(name, e.name) && Objects.equals(path, e.path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, path);
    }

    private static int compareByPath(Element left, Element right) {
        if (left == right) {
            return 0;
        }
        if (left == null) {
            return -1;
        }
        if (right == null) {
            return 1;
        }
        int pathComparison = Strings.CS.compare(left.path, right.path);
        if (pathComparison != 0) {
            return pathComparison;
        }
        return Strings.CS.compare(left.name, right.name);
    }

}
