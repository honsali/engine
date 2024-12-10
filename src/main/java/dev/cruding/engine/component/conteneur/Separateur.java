package dev.cruding.engine.component.conteneur;

import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.Element;

public class Separateur extends dev.cruding.engine.component.Component {

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

    public void addCloseTag(ViewFlow flow, int level) {

    }

}
