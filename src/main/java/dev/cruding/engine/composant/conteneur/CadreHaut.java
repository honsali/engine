package dev.cruding.engine.composant.conteneur;

import dev.cruding.engine.composant.Composant;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.flow.ViewFlow;

public class CadreHaut extends Conteneur {

    public CadreHaut(Element element, Composant... listeComposant) {
        super(element, listeComposant);
    }

    public void addImport(ViewFlow flow) {
        flow.addJsImport("{CadreHaut}", "waxant");
    }

    public boolean addOpenTag(ViewFlow flow, int level) {
        indent(flow, level).append("<CadreHaut").append(titre()).append(">");
        return false;
    }

    public void addCloseTag(ViewFlow flow, int level) {
        indent(flow, level).append("</CadreHaut>");
    }

}
