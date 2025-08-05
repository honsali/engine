package dev.cruding.engine.composant.conteneur;

import dev.cruding.engine.composant.Composant;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entite.Entite;
import dev.cruding.engine.flow.ViewFlow;

public class PlaqueEtat extends Composant {

    public PlaqueEtat(Element element, Entite entite) {
        super(element, entite);
    }

    public void addScript(ViewFlow f) {
        f.addSelector(entite.lname);
    }
}
