package dev.cruding.engine.composant.conteneur;

import dev.cruding.engine.composant.Composant;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.flow.ViewFlow;

public class Div extends Composant {

    public Div(Element element, Composant... ComposantList) {
        super(element, ComposantList);
    }

    public void addImport(ViewFlow flow) {

    }

    public boolean addOpenTag(ViewFlow flow, int level) {
        indent(flow, level).append("<div>");
        return false;
    }

    public void addCloseTag(ViewFlow flow, int level) {
        indent(flow, level).append("</div>");
    }

}
