package dev.cruding.engine.action.impl;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entite.Entite;

public class AppliquerFiltre extends Action {

    public AppliquerFiltre(Entite entite, Element element) {
        super(ActionType.UCA, "appliquerFiltre", entite, element);
        inViewOnly();
    }


}
