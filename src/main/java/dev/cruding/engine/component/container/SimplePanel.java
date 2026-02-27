package dev.cruding.engine.component.container;

import dev.cruding.engine.component.Component;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.flow.ViewFlow;

public class SimplePanel extends Container {

    public SimplePanel(Element element, Component... componentList) {
        super(element, componentList);
    }

    public void addImport(ViewFlow flow) {
        flow.addJsImport("{CadreSimple}", "waxant");
    }

    public boolean addOpenTag(ViewFlow flow, int level) {
        indent(flow, level).append("<CadreSimple").append(title());
        if (width != null) {
            flow.totalUi().__(" largeur=\"").append(width).append("\"");
        }
        if (margin != null) {
            flow.totalUi().__(" marge=\"").append(margin).append("\"");
        }
        flow.totalUi().__(">");
        return false;
    }

    public void addCloseTag(ViewFlow flow, int level) {
        indent(flow, level).append("</CadreSimple>");
    }

}
