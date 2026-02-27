package dev.cruding.engine.component.container;

import dev.cruding.engine.component.Component;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.flow.ViewFlow;

public class Separator extends Component {

    private int height = 20;

    public Separator(Element element) {
        super(element);
    }

    public Separator(Element element, int height) {
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
