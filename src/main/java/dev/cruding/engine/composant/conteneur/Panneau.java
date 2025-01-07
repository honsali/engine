package dev.cruding.engine.composant.conteneur;

import dev.cruding.engine.composant.Composant;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entite.Entite;
import dev.cruding.engine.flow.ViewFlow;

public class Panneau extends Conteneur {

    public boolean plaqueEtat = false;
    public BlocAction blocAction = null;

    public Panneau(Element element, Composant... ComposantList) {
        super(element, ComposantList);
    }

    public Panneau(Element element, Entite entite, Composant... ComposantList) {
        super(element, entite, ComposantList);
    }

    public void addImport(ViewFlow flow) {
        flow.addJsImport("{ Panneau }", "waxant");

    }

    public boolean addOpenTag(ViewFlow flow, int level) {
        indent(flow, level).append("<Panneau").append(titre());

        if (plaqueEtat) {
            flow.addToUi(" etat={").append(entite.lname).append("?.etat?.libelle}");
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

    public Panneau blocAction(Composant... ComposantList) {
        this.blocAction = new BlocAction(element, ComposantList);
        return this;
    }

}
