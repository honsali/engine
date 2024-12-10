package dev.cruding.engine.component.conteneur;

import dev.cruding.engine.component.Component;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.Element;

public class Bloc extends Component {

    public String largeur = null;

    public Bloc(Element element, Component... componentList) {
        super(element, componentList);
    }

    public Bloc largeur(String largeur) {
        this.largeur = largeur;
        return this;
    }

    public void addImport(ViewFlow flow) {
        flow.addJsImport("{Bloc}", "waxant");

    }

    public boolean addOpenTag(ViewFlow flow, int level) {
        indent(flow, level).append("<Bloc");
        if (largeur != null) {
            flow.addToUi(" largeur=\"").append(largeur).append("\"");
        }
        flow.addToUi(">");
        return false;
    }

    public void addCloseTag(ViewFlow flow, int level) {
        indent(flow, level).append("</Bloc>");
    }

}
