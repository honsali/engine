package dev.cruding.engine.component.conteneur;

import java.util.Arrays;
import dev.cruding.engine.component.Component;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.Element;

public class MenuOnglet extends Component {

    public MenuOnglet(Element element, Component... componentList) {
        super(element);
        this.componentList = Arrays.asList(componentList).stream().map(c -> new Onglet(element, c)).toArray(Onglet[]::new);
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
