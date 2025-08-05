package dev.cruding.engine.element;

import org.apache.commons.lang3.StringUtils;
import dev.cruding.engine.composant.Composant;
import dev.cruding.engine.flow.Flow;
import dev.cruding.engine.flow.ViewFlow;

public class ComposantRepresentantUnElement extends Composant {

    public Element subElement;
    public Element parentElement;

    public ComposantRepresentantUnElement(Element parentElement, Element subElement) {
        super(parentElement);
        this.subElement = subElement;
        this.parentElement = parentElement;
        this.nom = subElement.name;

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

    public ComposantRepresentantUnElement parForm() {
        this.subElement.parForm = true;
        return this;
    }

    public ComposantRepresentantUnElement parProp(String parProp) {
        this.subElement.parProp = parProp;
        return this;
    }

    public ComposantRepresentantUnElement parEntite() {
        this.subElement.parEntite();
        return this;
    }

    public void addScript(ViewFlow f) {
        if (this.subElement.parForm) {
            f.useForm(false);
        }
    }

    public boolean addOpenTag(ViewFlow flow, int level) {
        indent(flow, level).append("<").append(subElement.name);
        if (subElement.parForm) {
            flow.totalUi().__(" form={form}");
        }
        if (subElement.parProp != null) {
            flow.totalUi().__(" " + subElement.parProp + "={" + subElement.parProp + "}");
        }
        flow.totalUi().__(" />");
        return false;
    }

    public void appendContent(ViewFlow vf, Flow flow) {
        addImport(vf);
        flow.__("<").append(subElement.name);
        if (subElement.parForm) {
            flow.__(" form={form}");
        }
        if (subElement.parProp != null) {
            flow.__(" " + StringUtils.substringBefore(subElement.parProp, ":") + "={" + StringUtils.substringAfter(subElement.parProp, ":") + "}");
        }
        flow.__(" />");
    }
}
