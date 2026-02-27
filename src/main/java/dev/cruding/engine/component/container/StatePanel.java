package dev.cruding.engine.component.container;

import dev.cruding.engine.component.Component;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.flow.ViewFlow;

public class StatePanel extends Component {

    public StatePanel(Element element, Entity entity) {
        super(element, entity);
    }

    public void addScript(ViewFlow f) {
        f.addSelector(entity.lname);
    }
}
