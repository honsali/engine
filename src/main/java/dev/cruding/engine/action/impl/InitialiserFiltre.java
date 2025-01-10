package dev.cruding.engine.action.impl;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entite.Entite;

public class InitialiserFiltre extends Action {

    public InitialiserFiltre(Entite entite, Element element) {
        super(ActionType.UCA, "initialiserFiltre", entite, element);
        inViewOnly();
        init();
    }


}
