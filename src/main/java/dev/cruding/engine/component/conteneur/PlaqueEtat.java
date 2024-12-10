package dev.cruding.engine.component.conteneur;

import dev.cruding.engine.component.Component;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.Element;

public class PlaqueEtat extends Component {

    public PlaqueEtat(Element element, Entity entity) {
        super(element, entity);
    }


    public void addScript(ViewFlow f) {
        f.addSpecificSelector(entity.lname, "./Mdl" + element.page.uc);
    }
}
