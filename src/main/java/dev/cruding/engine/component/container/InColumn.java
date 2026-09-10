package dev.cruding.engine.component.container;

import java.util.Arrays;
import java.util.Objects;
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
        super(element);
        this.componentList = checkedContent(componentList);
        this.width = Integer.toString(24 / columnNumber);
    }

    public InColumn content(Component... componentList) {
        this.componentList = checkedContent(componentList);
        return this;
    }

    @Override
    public InColumn name(String name) {
        this.name = name;
        return this;
    }

    private Component[] checkedContent(Component[] componentList) {
        if (componentList == null || Arrays.stream(componentList).anyMatch(Objects::isNull)) {
            throw new IllegalArgumentException("InColumn cannot contain null components: column positions must be preserved.");
        }
        return componentList.clone();
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

    @Override
    protected void addBody(ViewFlow flow, int level) {
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
    }

    public void addCloseTag(ViewFlow flow, int level) {
        indent(flow, level).append("</Row>");
    }

    public InColumn margin(String margin) {
        this.margin = margin;
        return this;
    }

    public InColumn columnNumber(int columnNumber) {
        this.columnNumber = columnNumber;
        this.width = Integer.toString(24 / columnNumber);
        this.flexWidth = null;
        this.spanWidth = null;
        return this;
    }

    public InColumn flex(String... width) {
        this.columnNumber = width.length;
        this.width = null;
        this.flexWidth = width;
        this.spanWidth = null;
        return this;
    }

    public InColumn spans(int... width) {
        this.columnNumber = width.length;
        this.width = null;
        this.flexWidth = null;
        this.spanWidth = width;
        return this;
    }

}
