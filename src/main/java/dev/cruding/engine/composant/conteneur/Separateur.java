package dev.cruding.engine.composant.conteneur;

import dev.cruding.engine.composant.Composant;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.flow.ViewFlow;

public class Separateur extends Composant {

    private int height = 20;

    public Separateur(Element element) {
        super(element);
    }

    public Separateur(Element element, int height) {
        super(element);
        this.height = height;
    }

    public void addImport(ViewFlow flow) {
        flow.addJsImport("{Separateur}", "waxant");
    }

    public boolean addOpenTag(ViewFlow flow, int level) {
        indent(flow, level).append("<Separateur top=\"" + height + "\" />");
        return false;
    }

    public void addCloseTag(ViewFlow flow, int level) {}

}
