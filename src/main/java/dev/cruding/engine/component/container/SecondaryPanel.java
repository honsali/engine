package dev.cruding.engine.component.container;

import dev.cruding.engine.component.Component;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.flow.ViewFlow;

public class SecondaryPanel extends Container<SecondaryPanel> {

    public String title;
    public String width;
    public String margin;

    public SecondaryPanel title(String title) {
        this.title = title;
        return this;
    }

    public SecondaryPanel width(String width) {
        this.width = width;
        return this;
    }

    public SecondaryPanel margin(String margin) {
        this.margin = margin;
        return this;
    }

    public SecondaryPanel(Element element, Component... componentList) {
        super(element, componentList);
    }

    public void addImport(ViewFlow flow) {
        flow.addJsImport("{CadreNormal}", "waxant");
    }

    public boolean addOpenTag(ViewFlow flow, int level) {
        indent(flow, level).append("<CadreNormal").append(titleAttribute(title));
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
        indent(flow, level).append("</CadreNormal>");
    }

}
