package dev.cruding.engine.component.conteneur;

import java.util.Arrays;

import dev.cruding.engine.component.Component;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.Page;

public class MenuOnglet extends Component {

    public MenuOnglet(Page page, Component... componentList) {
        super(page);
        this.componentList = Arrays.asList(componentList).stream().map(c -> new Onglet(page, c)).toArray(Onglet[]::new);
    }

    public void addImport(ViewFlow flow) {
        flow.addJsImport("{MenuOnglet}", "waxant");

    }

    public void addScript(ViewFlow flow) {

    }

    public void addOpenTag(ViewFlow flow, int level) {
        flow.addToUi(indent[level]).append("<MenuOnglet>");

    }

    public void addCloseTag(ViewFlow flow, int level) {
        flow.addToUi(indent[level]).append("</MenuOnglet>");
    }

}
