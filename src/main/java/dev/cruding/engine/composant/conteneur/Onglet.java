package dev.cruding.engine.composant.conteneur;

import dev.cruding.engine.composant.Composant;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.flow.ViewFlow;

public class Onglet extends Conteneur {

    public Onglet(Element element, Composant... ComposantList) {
        super(element, ComposantList);
    }

    public void addImport(ViewFlow flow) {
        flow.addJsImport("{Onglet}", "waxant");
    }

    public boolean addOpenTag(ViewFlow flow, int level) {
        indent(flow, level).append("<Onglet key=\"").append(titre()).append("\" >");
        return false;
    }

    public void addCloseTag(ViewFlow flow, int level) {
        indent(flow, level).append("</Onglet>");
    }

}
