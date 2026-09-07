package dev.cruding.engine.component.container;

import dev.cruding.engine.component.Component;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.Context;
import dev.cruding.engine.gen.Page;

public class Section extends Container {

    public Page backPage = null;
    public boolean statePanel = false;
    public Component actionBlock = null;

    public Section(Element element, Component... componentList) {
        super(element, componentList);
    }

    public Section(Element element, Entity entity, Component... componentList) {
        super(element, entity, componentList);
    }

    public void addImport(ViewFlow flow) {
        flow.addJsImport("{ Section }", "waxant");
        if (backPage != null) {
            flow.addJsImport("{ " + backPage.name + " }", backPage.module.pageList(element.path, inElement));
        }
        if (statePanel) {
            flow.addJsImport("{ PlaqueEtat }", "waxant");
        }
    }

    public boolean addOpenTag(ViewFlow flow, int level) {
        indent(flow, level).append("<Section").append(title());
        if (backPage != null) {
            if (actionBlock != null) {
                indent(flow, level + 1);
            }
            flow.totalUi().__(" backPage={").append(backPage.name).append("}");
            Context.getInstance().addLabel(element.page.module.uname, "Uc" + element.page.uc + ".retour" + backPage.name, "Retour");

        }
        if (margin != null) {
            flow.totalUi().__(" marge=\"").append(margin).append("\"");
        }
        if (statePanel) {
            flow.totalUi().__(" blocAction={<PlaqueEtat entity={").append(entity.lname).append("} />}");
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
        indent(flow, level).append("</Section>");
    }

    public Section statePanel() {
        this.statePanel = true;
        return this;
    }

    public Section actionBlock(Component actionBlock) {
        this.actionBlock = actionBlock;
        return this;
    }

    public Section backPage(Page backPage) {
        this.backPage = backPage;
        return this;
    }

}
