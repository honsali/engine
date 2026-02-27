package dev.cruding.engine.component.container;

import dev.cruding.engine.component.Component;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.flow.ViewFlow;

public class InColumn extends Component {

    public int columnNumber = 2;
    public String margin = "20";
    public String width;
    public String[] flexWidth;
    public int[] spanWidth;

    public InColumn(Element element, Component... componentList) {
        super(element, componentList);
        this.width = null;// Integer.toString(24 / columnNumber);
    }

    public void addImport(ViewFlow flow) {
        flow.addJsImport("{ Row }", "antd");
        flow.addJsImport("{ Col }", "antd");
    }

    public boolean addOpenTag(ViewFlow flow, int level) {
        indent(flow, level).append("<Row");
        flow.totalUi().__(" gutter={").append(margin).append("}");
        flow.totalUi().__(">");

        return false;
    }

    public void addContent(Component fatherComponent, ViewFlow flow, boolean inline, int level) {
        inElement = inElement || (fatherComponent != null && fatherComponent.inElement);
        this.fatherComponent = fatherComponent;
        this.inline = inline;
        addImport(flow);
        addScript(flow);
        if (level == 1) {
            flow.totalUi().__("(");
        }
        boolean childInline = addOpenTag(flow, level);
        if (!isElement) {
            if (componentList != null) {
                for (int i = 0; i < componentList.length; i++) {
                    Component component = componentList[i];
                    if (flexWidth != null) {
                        indent(flow, level + 1).append("<Col flex=\"").append(flexWidth[i]).append("\">");
                    } else if (spanWidth != null) {
                        indent(flow, level + 1).append("<Col span={").append(String.valueOf(spanWidth[i])).append("}>");
                    } else {
                        indent(flow, level + 1).append("<Col span={").append(width).append("}>");
                    }
                    component.addContent(this, flow, childInline, level + 2);
                    indent(flow, level + 1).append("</Col>");
                }
            }
        }
        addCloseTag(flow, level);
        if (level == 1) {
            indent(flow, 0).append(");");
        }
    }

    public void addCloseTag(ViewFlow flow, int level) {
        indent(flow, level).append("</Row>");
    }

    public InColumn margin(String margin) {
        this.margin = margin;
        return this;
    }

    public InColumn width(int columnNumber) {
        this.columnNumber = columnNumber;
        this.width = Integer.toString(24 / columnNumber);
        this.flexWidth = null;
        this.spanWidth = null;
        return this;
    }

    public InColumn width(String... width) {
        this.columnNumber = width.length;
        this.width = null;
        this.flexWidth = width;
        this.spanWidth = null;
        return this;
    }

    public InColumn width(int... width) {
        this.columnNumber = width.length;
        this.width = null;
        this.flexWidth = null;
        this.spanWidth = width;
        return this;
    }

}
