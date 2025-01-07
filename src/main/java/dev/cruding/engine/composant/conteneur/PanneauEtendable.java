package dev.cruding.engine.composant.conteneur;

import dev.cruding.engine.composant.Composant;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entite.Entite;
import dev.cruding.engine.flow.ViewFlow;

public class PanneauEtendable extends Conteneur {

    public boolean open = false;

    public PanneauEtendable(Element element, Composant... ComposantList) {
        super(element, ComposantList);
    }

    public PanneauEtendable(Element element, Entite entite, Composant... ComposantList) {
        super(element, entite, ComposantList);
    }

    public void addImport(ViewFlow flow) {
        flow.addJsImport("{ PanneauEtendable }", "waxant");

    }

    public boolean addOpenTag(ViewFlow flow, int level) {
        indent(flow, level).append("<PanneauEtendable").append(titre());

        if (open) {
            flow.addToUi(" open={true}");
        }

        flow.addToUi(">");
        return false;
    }

    public void addCloseTag(ViewFlow flow, int level) {
        indent(flow, level).append("</PanneauEtendable>");
    }

    public PanneauEtendable open() {
        this.open = true;
        return this;
    }

}
