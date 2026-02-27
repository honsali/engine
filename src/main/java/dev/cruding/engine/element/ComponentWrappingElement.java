package dev.cruding.engine.element;

import org.apache.commons.lang3.StringUtils;
import dev.cruding.engine.component.Component;
import dev.cruding.engine.flow.Flow;
import dev.cruding.engine.flow.ViewFlow;

public class ComponentWrappingElement extends Component {

    public Element subElement;
    public Element parentElement;

    public ComponentWrappingElement(Element parentElement, Element subElement) {
        super(parentElement);
        this.subElement = subElement;
        this.parentElement = parentElement;
        this.name = subElement.name;

    }

    public void addImport(ViewFlow flow) {
        if (subElement.relativePath.startsWith("../")) {
            flow.addJsImport(subElement.name, subElement.relativePath + "/" + subElement.name);
        } else if (subElement.relativePath.startsWith("./")) {
            flow.addJsImport(subElement.name, subElement.relativePath + "/" + subElement.name);
        } else if (subElement.relativePath.equals(parentElement.relativePath)) {
            flow.addJsImport(subElement.name, "./" + subElement.name);
        } else if (subElement.page == null) {
            flow.addJsImport(subElement.name, subElement.relativePath + "/" + subElement.name);
        } else if (subElement.relativePath.startsWith("modules")) {
            flow.addJsImport(subElement.name, subElement.relativePath + "/" + subElement.name);
        } else {
            flow.addJsImport(subElement.name, "." + subElement.relativePath + "/" + subElement.name);
        }
    }

    public ComponentWrappingElement byForm() {
        this.subElement.byForm = true;
        return this;
    }

    public ComponentWrappingElement byProp(String byProp) {
        this.subElement.byProp = byProp;
        return this;
    }

    public ComponentWrappingElement byEntity() {
        this.subElement.byEntity();
        return this;
    }

    public void addScript(ViewFlow f) {
        if (this.subElement.byForm) {
            f.useForm(false);
        }
    }

    public boolean addOpenTag(ViewFlow flow, int level) {
        indent(flow, level).append("<").append(subElement.name);
        if (subElement.byForm) {
            flow.totalUi().__(" form={form}");
        }
        if (subElement.byProp != null) {
            flow.totalUi().__(" " + subElement.byProp + "={" + subElement.byProp + "}");
        }
        flow.totalUi().__(" />");
        return false;
    }

    public void appendContent(ViewFlow vf, Flow flow) {
        addImport(vf);
        flow.__("<").append(subElement.name);
        if (subElement.byForm) {
            flow.__(" form={form}");
        }
        if (subElement.byProp != null) {
            flow.__(" " + StringUtils.substringBefore(subElement.byProp, ":") + "={" + StringUtils.substringAfter(subElement.byProp, ":") + "}");
        }
        flow.__(" />");
    }
}
