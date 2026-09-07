package dev.cruding.engine.component.container;

import org.apache.commons.lang3.StringUtils;
import dev.cruding.engine.component.Component;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.Context;

public class Tab extends Container {

    public Tab(Element element, Component... componentList) {
        super(element, componentList);
    }

    public void addImport(ViewFlow flow) {
        flow.addJsImport("{Onglet}", "waxant");
    }

    public boolean addOpenTag(ViewFlow flow, int level) {
        String label = StringUtils.capitalize(StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(title), " "));
        Context.getInstance().addLabel(element.page.module.uname, "onglet." + title, label);
        indent(flow, level).append("<Onglet key=\"").append(title).append("\" >");
        return false;
    }

    public void addCloseTag(ViewFlow flow, int level) {
        indent(flow, level).append("</Onglet>");
    }

}
