package dev.cruding.engine.composant.conteneur;

import dev.cruding.engine.composant.Composant;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.flow.ViewFlow;

public class Bloc extends Composant {

    public String largeur = null;

    public Bloc(Element element, Composant... ComposantList) {
        super(element, ComposantList);
    }

    public void addImport(ViewFlow flow) {
        flow.addJsImport("{Bloc}", "waxant");
    }

    public boolean addOpenTag(ViewFlow flow, int level) {
        indent(flow, level).append("<Bloc");
        if (largeur != null) {
            flow.addToUi(" largeur=\"").append(largeur).append("\"");
        }
        flow.addToUi(">");
        return false;
    }

    public void addCloseTag(ViewFlow flow, int level) {
        indent(flow, level).append("</Bloc>");
    }

    public Bloc largeur(String largeur) {
        this.largeur = largeur;
        return this;
    }

}
