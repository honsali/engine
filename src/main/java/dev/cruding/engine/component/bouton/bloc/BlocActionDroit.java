package dev.cruding.engine.component.bouton.bloc;

import dev.cruding.engine.component.Component;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.Page;

public class BlocActionDroit extends Component {

    public BlocActionDroit(Page page, Component... componentList) {
        super(page, componentList);
    }

    public void addImport(ViewFlow flow) {
        flow.addJsImport("{BlocActionDroit}", "waxant");

    }

    public void addOpenTag(ViewFlow flow, int level) {
        flow.addToUi(indent[level]).append("<BlocActionDroit>");
    }

    public void addCloseTag(ViewFlow flow, int level) {
        flow.addToUi(indent[level]).append("</BlocActionDroit>");
    }

}
