package dev.cruding.engine.composant.conteneur;

import dev.cruding.engine.composant.Composant;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.flow.ViewFlow;

public class Span extends Composant {

    private String texte;

    public Span(Element element, String texte) {
        super(element);
        this.texte = texte;
    }

    public void addImport(ViewFlow flow) {

    }

    public boolean addOpenTag(ViewFlow flow, int level) {
        indent(flow, level).append("<span>").append(texte).append("</span>");
        return false;
    }

    public void addCloseTag(ViewFlow flow, int level) {}

}
