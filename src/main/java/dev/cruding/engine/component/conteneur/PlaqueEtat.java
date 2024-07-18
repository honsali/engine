package dev.cruding.engine.component.conteneur;

import dev.cruding.engine.component.Component;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.Page;

public class PlaqueEtat extends Component {

    public PlaqueEtat(Page page, Entity entity) {
        super(page, entity);
    }

    public void addOpenTag(ViewFlow flow, int level) {

    }

    public void addCloseTag(ViewFlow flow, int level) {

    }

    public void addImport(ViewFlow flow) {
    }

    public void addScript(ViewFlow f) {
        f.addSpecificSelector(entity.lname, "./Mdl" + page.uc);
    }
}
