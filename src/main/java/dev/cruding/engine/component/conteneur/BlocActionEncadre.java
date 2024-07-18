package dev.cruding.engine.component.conteneur;

import dev.cruding.engine.component.Component;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.Page;

public class BlocActionEncadre extends Component {

    public BlocActionEncadre(Page page, Component... componentList) {
        super(page, componentList);
    }

    public void addImport(ViewFlow flow) {
        flow.addJsImport("{BlocActionEncadre}", "waxant");

    }

    public void addOpenTag(ViewFlow flow, int level) {
        flow.addToUi(indent[level]).append("<BlocActionEncadre>");
    }

    public void addCloseTag(ViewFlow flow, int level) {
        flow.addToUi(indent[level]).append("</BlocActionEncadre>");
    }

}
