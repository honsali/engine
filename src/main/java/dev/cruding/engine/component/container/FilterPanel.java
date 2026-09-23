package dev.cruding.engine.component.container;

import dev.cruding.engine.component.Component;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.flow.ViewFlow;

public class FilterPanel extends Container<FilterPanel> {

    public String title;

    public FilterPanel title(String title) {
        this.title = title;
        return this;
    }

    public FilterPanel(Element element, Entity entity, Component... componentList) {
        super(element, entity, componentList);
    }

    public boolean addOpenTag(ViewFlow flow, int level) {
        indent(flow, level).append("<Filtre").append(titleAttribute(title));
        flow.totalUi().__(">");
        return false;
    }

    public void addCloseTag(ViewFlow flow, int level) {
        indent(flow, level).append("</Filtre>");
    }

    public void addImport(ViewFlow flow) {
        flow.addJsImport("{ Filtre }", "waxant");
        flow.useForm(entity);
        flow.useEffect();
        flow.addJsImport("{ BlocAction }", "waxant");
        flow.addJsImport("{ ActionUcInitialiserFiltre }", "waxant");
        flow.addJsImport("{ ActionUcAppliquerFiltre }", "waxant");
    }


}
