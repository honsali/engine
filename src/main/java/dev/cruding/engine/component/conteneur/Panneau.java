package dev.cruding.engine.component.conteneur;

import dev.cruding.engine.component.Component;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.Element;

public class Panneau extends Conteneur {

    public boolean plaqueEtat = false;
    public BlocAction blocAction = null;

    public Panneau(Element element, Component... componentList) {
        super(element, componentList);
    }

    public Panneau(Element element, Entity entity, Component... componentList) {
        super(element, entity, componentList);
    }

    public void addImport(ViewFlow flow) {
        flow.addJsImport("{ Panneau }", "waxant");

    }

    public boolean addOpenTag(ViewFlow flow, int level) {
        indent(flow, level).append("<Panneau").append(titre());

        if (plaqueEtat) {
            flow.addToUi(" etat={").append(entity.lname).append("?.etat?.libelle}");
        }

        if (blocAction != null) {
            indent(flow, level + 1).append("blocAction={");
            blocAction.addContent(this, flow, level + 2);
            indent(flow, level + 1).append("}");
            indent(flow, level).append(">");
        } else {
            flow.addToUi(">");
        }
        return false;
    }

    public void addCloseTag(ViewFlow flow, int level) {
        indent(flow, level).append("</Panneau>");
    }

    public Panneau plaqueEtat() {
        this.plaqueEtat = true;
        return this;
    }

    public Panneau blocAction(Component... componentList) {
        this.blocAction = new BlocAction(element, componentList);
        return this;
    }

}
