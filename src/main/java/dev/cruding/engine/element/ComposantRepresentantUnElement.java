package dev.cruding.engine.element;

import dev.cruding.engine.composant.Composant;
import dev.cruding.engine.flow.ViewFlow;

public class ComposantRepresentantUnElement extends Composant {

    public Element subElement;
    public Element parentElement;

    public ComposantRepresentantUnElement(Element parentElement, Element subElement) {
        super(parentElement);
        this.subElement = subElement;
        this.parentElement = parentElement;
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
        } else {
            flow.addJsImport(subElement.name, "." + subElement.relativePath + "/" + subElement.name);
        }
    }

    public ComposantRepresentantUnElement byForm() {
        this.subElement.byForm = true;
        return this;
    }

    public ComposantRepresentantUnElement byProp(String byProp) {
        this.subElement.byProp = byProp;
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
            flow.addToUi(" form={form}");
        }
        if (subElement.byProp != null) {
            flow.addToUi(" " + subElement.byProp + "={" + subElement.byProp + "}");
        }
        flow.addToUi(" />");
        return false;
    }

}
