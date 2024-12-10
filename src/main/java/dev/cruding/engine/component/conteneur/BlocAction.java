package dev.cruding.engine.component.conteneur;

import dev.cruding.engine.component.Component;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.Element;

public class BlocAction extends Component {

    public BlocAction(Element element, Component... componentList) {
        super(element, componentList);
    }

    public void addImport(ViewFlow flow) {
        flow.addJsImport("{BlocAction}", "waxant");


    }

    public boolean addOpenTag(ViewFlow flow, int level) {
        indent(flow, level).append("<BlocAction>");
        return false;
    }

    public void addCloseTag(ViewFlow flow, int level) {
        indent(flow, level).append("</BlocAction>");
    }

}
