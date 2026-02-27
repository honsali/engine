package dev.cruding.engine.component.container;

import java.util.Arrays;
import dev.cruding.engine.component.Component;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.flow.ViewFlow;

public class TabMenu extends Component {

    public TabMenu(Element element, Component... componentList) {
        super(element);
        this.componentList = Arrays.asList(componentList).stream().map(c -> new Tab(element, c).title(c.name)).toArray(Tab[]::new);
    }

    public void addImport(ViewFlow flow) {
        flow.addJsImport("{MenuOnglet}", "waxant");
    }

    public boolean addOpenTag(ViewFlow flow, int level) {
        indent(flow, level).append("<MenuOnglet>");
        return false;
    }

    public void addCloseTag(ViewFlow flow, int level) {
        indent(flow, level).append("</MenuOnglet>");
    }

}
