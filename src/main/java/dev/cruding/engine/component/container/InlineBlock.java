package dev.cruding.engine.component.container;

import dev.cruding.engine.component.Component;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.flow.ViewFlow;

public class InlineBlock extends Container {


    public InlineBlock(Element element, Component... componentList) {
        super(element, componentList);
    }

    public void addImport(ViewFlow flow) {
        flow.addJsImport("{ BlocInline }", "waxant");
    }

    public boolean addOpenTag(ViewFlow flow, int level) {
        indent(flow, level).append("<BlocInline");
        flow.totalUi().__(">");
        return false;
    }

    public void addCloseTag(ViewFlow flow, int level) {
        indent(flow, level).append("</BlocInline>");
    }



}
