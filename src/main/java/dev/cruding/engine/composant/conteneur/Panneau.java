package dev.cruding.engine.composant.conteneur;

import dev.cruding.engine.composant.Composant;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entite.Entite;
import dev.cruding.engine.flow.ViewFlow;

public class Panneau extends Conteneur {

    public boolean plaqueEtat = false;
    public BlocAction blocAction = null;

    public Panneau(Element element, Composant... listeComposant) {
        super(element, listeComposant);
    }

    public Panneau(Element element, Entite entite, Composant... listeComposant) {
        super(element, entite, listeComposant);
    }

    public void addImport(ViewFlow flow) {
        flow.addJsImport("{ Panneau }", "waxant");

    }

    public boolean addOpenTag(ViewFlow flow, int level) {
        indent(flow, level).append("<Panneau").append(titre());

        if (plaqueEtat) {
            flow.totalUi().__(" etat={").append(entite.lname).append("?.etat?.libelle}");
        }

        if (blocAction != null) {
            indent(flow, level + 1).append("blocAction={");
            blocAction.addContent(this, flow, level + 2);
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

    public Panneau plaqueEtat() {
        this.plaqueEtat = true;
        return this;
    }

    public Panneau blocAction(Composant... listeComposant) {
        this.blocAction = new BlocAction(element, listeComposant);
        return this;
    }

}
