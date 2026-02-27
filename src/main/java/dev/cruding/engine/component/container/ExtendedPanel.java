package dev.cruding.engine.component.container;

import dev.cruding.engine.component.Component;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.flow.ViewFlow;

public class ExtendedPanel extends Container {

    public boolean open = false;

    public ExtendedPanel(Element element, Component... componentList) {
        super(element, componentList);
    }

    public ExtendedPanel(Element element, Entity entity, Component... componentList) {
        super(element, entity, componentList);
    }

    public void addImport(ViewFlow flow) {
        flow.addJsImport("{ PanneauEtendable }", "waxant");

    }

    public boolean addOpenTag(ViewFlow flow, int level) {
        indent(flow, level).append("<PanneauEtendable").append(title());

        if (open) {
            flow.totalUi().__(" open={true}");
        }

        flow.totalUi().__(">");
        return false;
    }

    public void addCloseTag(ViewFlow flow, int level) {
        indent(flow, level).append("</PanneauEtendable>");
    }

    public ExtendedPanel open() {
        this.open = true;
        return this;
    }

}
