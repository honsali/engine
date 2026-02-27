package dev.cruding.engine.component.container;

import dev.cruding.engine.component.Component;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.flow.ViewFlow;

public class Span extends Component {

    private String text;

    public Span(Element element, String text) {
        super(element);
        this.text = text;
    }

    public void addImport(ViewFlow flow) {

    }

    public boolean addOpenTag(ViewFlow flow, int level) {
        indent(flow, level).append("<span>").append(text).append("</span>");
        return false;
    }

    public void addCloseTag(ViewFlow flow, int level) {}

}
