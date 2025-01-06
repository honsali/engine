package dev.cruding.engine.component.conteneur;

import dev.cruding.engine.component.Component;
import dev.cruding.engine.entite.Entite;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.Element;

public class PlaqueEtat extends Component {

    public PlaqueEtat(Element element, Entite entite) {
        super(element, entite);
    }


    public void addScript(ViewFlow f) {
        f.addSpecificSelector(entite.lname, "./Mdl" + element.page.uc);
    }
}
