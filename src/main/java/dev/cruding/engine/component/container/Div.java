package dev.cruding.engine.component.container;

import dev.cruding.engine.component.Component;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.flow.ViewFlow;

public class Div extends Component {

    private String text;

    public Div(Element element, String text) {
        super(element);
        this.text = text;
    }

    public Div(Element element, Component... componentList) {
        super(element, componentList);
    }

    public void addImport(ViewFlow flow) {

    }

    public boolean addOpenTag(ViewFlow flow, int level) {
        if (text == null) {
            indent(flow, level).append("<div>");
        } else {
            indent(flow, level).append("<div>").append(text).append("</div>");
        }
        return false;
    }

    public void addCloseTag(ViewFlow flow, int level) {
        if (text == null) {
            indent(flow, level).append("</div>");
        }
    }

}
