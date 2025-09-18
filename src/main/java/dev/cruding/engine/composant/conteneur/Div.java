package dev.cruding.engine.composant.conteneur;

import dev.cruding.engine.composant.Composant;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.flow.ViewFlow;

public class Div extends Composant {

    private String texte;

    public Div(Element element, String texte) {
        super(element);
        this.texte = texte;
    }

    public Div(Element element, Composant... listeComposant) {
        super(element, listeComposant);
    }

    public void addImport(ViewFlow flow) {

    }

    public boolean addOpenTag(ViewFlow flow, int level) {
        if (texte == null) {
            indent(flow, level).append("<div>");
        } else {
            indent(flow, level).append("<div>").append(texte).append("</div>");
        }
        return false;
    }

    public void addCloseTag(ViewFlow flow, int level) {
        if (texte == null) {
            indent(flow, level).append("</div>");
        }
    }

}
