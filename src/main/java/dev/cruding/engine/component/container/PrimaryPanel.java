package dev.cruding.engine.component.container;

import dev.cruding.engine.component.Component;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.flow.ViewFlow;

public class PrimaryPanel extends Container<PrimaryPanel> {

    public String title;
    public String width;
    public String margin;

    public PrimaryPanel title(String title) {
        this.title = title;
        return this;
    }

    public PrimaryPanel width(String width) {
        this.width = width;
        return this;
    }

    public PrimaryPanel margin(String margin) {
        this.margin = margin;
        return this;
    }

    public PrimaryPanel(Element element, Component... componentList) {
        super(element, componentList);
    }

    public void addImport(ViewFlow flow) {
        flow.addJsImport("{CadreFort}", "waxant");
    }

    public boolean addOpenTag(ViewFlow flow, int level) {
        indent(flow, level).append("<CadreFort").append(titleAttribute(title));
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
        indent(flow, level).append("</CadreFort>");
    }

}
