package dev.cruding.engine.component.container;

import dev.cruding.engine.component.Component;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.flow.ViewFlow;

public class Panel extends Container {

    public boolean statePanel = false;
    public ActionBlock actionBlock = null;

    public Panel(Element element, Component... componentList) {
        super(element, componentList);
    }

    public Panel(Element element, Entity entity, Component... componentList) {
        super(element, entity, componentList);
    }

    public void addImport(ViewFlow flow) {
        flow.addJsImport("{ Panneau }", "waxant");

    }

    public boolean addOpenTag(ViewFlow flow, int level) {
        indent(flow, level).append("<Panneau").append(title());

        if (statePanel) {
            flow.totalUi().__(" etat={").append(entity.lname).append("?.etat?.libelle}");
        }

        if (actionBlock != null) {
            indent(flow, level + 1).append("blocAction={");
            actionBlock.addContent(this, flow, level + 2);
            indent(flow, level + 1).append("}");
            indent(flow, level).append(">");
        } else {
            flow.totalUi().__(">");
        }
        return false;
    }

    public void addCloseTag(ViewFlow flow, int level) {
        indent(flow, level).append("</Panneau>");
    }

    public Panel statePanel() {
        this.statePanel = true;
        return this;
    }

    public Panel actionBlock(Component... componentList) {
        this.actionBlock = new ActionBlock(element, componentList);
        return this;
    }

}
