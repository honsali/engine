package dev.cruding.engine.action.inViewOnly;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.action.inViewOnly.injection.ViewAppliquerFiltreInjection;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entite.Entite;

public class AppliquerFiltre extends Action {

    public Action action;

    public AppliquerFiltre(Entite entite, Element element, Action action) {
        super(ActionType.UCA, "appliquerFiltre", entite, element);
        this.action = action;
        inViewOnly();
    }


    public void overrideActionInjection() {
        viewActionInjection = new ViewAppliquerFiltreInjection(action);
    }

}
