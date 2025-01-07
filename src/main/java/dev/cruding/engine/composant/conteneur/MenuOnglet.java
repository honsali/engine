package dev.cruding.engine.composant.conteneur;

import java.util.Arrays;
import dev.cruding.engine.composant.Composant;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.flow.ViewFlow;

public class MenuOnglet extends Composant {

    public MenuOnglet(Element element, Composant... ComposantList) {
        super(element);
        this.ComposantList = Arrays.asList(ComposantList).stream().map(c -> new Onglet(element, c)).toArray(Onglet[]::new);
    }

    public void addImport(ViewFlow flow) {
        flow.addJsImport("{MenuOnglet}", "waxant");
    }

    public boolean addOpenTag(ViewFlow flow, int level) {
        indent(flow, level).append("<MenuOnglet>");
        return false;
    }

    public void addCloseTag(ViewFlow flow, int level) {
        indent(flow, level).append("</MenuOnglet>");
    }

}
