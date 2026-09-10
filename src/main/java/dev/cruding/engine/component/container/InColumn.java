package dev.cruding.engine.component.container;

import java.util.Arrays;
import java.util.Objects;
import dev.cruding.engine.component.Component;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.flow.ViewFlow;

public class InColumn extends Component {

    public int columnNumber = 2;
    public String margin = "20";

    public InColumn(Element element, Component... componentList) {
        super(element);
        this.componentList = Arrays.stream(checkedContent(componentList))
                .map(component -> new Column(component, null, null))
                .toArray(Component[]::new);
    }

    public InColumn column(Component component) {
        return addColumn(component, null, null);
    }

    public InColumn column(int span, Component component) {
        return addColumn(component, span, null);
    }

    public InColumn column(String flex, Component component) {
        return addColumn(component, null, flex);
    }

    private InColumn addColumn(Component component, Integer span, String flex) {
        if (component == null) {
            throw new IllegalArgumentException("InColumn cannot contain null components: column positions must be preserved.");
        }
        this.componentList = Arrays.copyOf(this.componentList, this.componentList.length + 1, Component[].class);
        this.componentList[this.componentList.length - 1] = new Column(component, span, flex);
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

    public void addCloseTag(ViewFlow flow, int level) {
        indent(flow, level).append("</Row>");
    }

    public InColumn margin(String margin) {
        this.margin = margin;
        return this;
    }

    public InColumn columnNumber(int columnNumber) {
        if (columnNumber <= 0) {
            throw new IllegalArgumentException("InColumn columnNumber must be positive.");
        }
        this.columnNumber = columnNumber;
        return this;
    }

    private class Column extends Component {

        private final Integer span;
        private final String flex;

        private Column(Component component, Integer span, String flex) {
            super(InColumn.this.element, component);
            this.span = span;
            this.flex = flex;
        }

        @Override
        public boolean addOpenTag(ViewFlow flow, int level) {
            if (flex != null) {
                indent(flow, level).append("<Col flex=\"").append(flex).append("\">");
            } else {
                int columnSpan = span != null ? span : 24 / columnNumber;
                indent(flow, level).append("<Col span={").append(String.valueOf(columnSpan)).append("}>");
            }
            return false;
        }

        @Override
        public void addCloseTag(ViewFlow flow, int level) {
            indent(flow, level).append("</Col>");
        }
    }

}
