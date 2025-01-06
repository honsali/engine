package dev.cruding.engine.component.conteneur;

import dev.cruding.engine.component.Component;
import dev.cruding.engine.entite.Entite;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.Element;

public class PanneauEtendable extends Conteneur {

    public boolean open = false;

    public PanneauEtendable(Element element, Component... componentList) {
        super(element, componentList);
    }

    public PanneauEtendable(Element element, Entite entite, Component... componentList) {
        super(element, entite, componentList);
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
