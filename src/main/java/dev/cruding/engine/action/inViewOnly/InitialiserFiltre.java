package dev.cruding.engine.action.inViewOnly;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.action.inViewOnly.injection.ViewInitialiserFiltreInjection;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entite.Entite;

public class InitialiserFiltre extends Action {

    public Action action;

    public InitialiserFiltre(Entite entite, Element element, Action action) {
        super(ActionType.UCA, "initialiserFiltre", entite, element);
        this.action = action;
        inViewOnly();
    }

    public void overrideActionInjection() {
        viewActionInjection = new ViewInitialiserFiltreInjection(action);
    }
}
