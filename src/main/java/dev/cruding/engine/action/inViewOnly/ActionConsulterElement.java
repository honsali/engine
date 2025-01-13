package dev.cruding.engine.action.inViewOnly;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.action.inViewOnly.injection.ViewConsulterElementInjection;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entite.Entite;

public class ActionConsulterElement extends Action {

    public ActionConsulterElement(Entite entite, Element element) {
        super(ActionType.NOUI, "consulter", entite, element);
    }

    public void overrideActionInjection() {
        viewActionInjection = new ViewConsulterElementInjection();
    }
}
