package dev.cruding.engine.action.recuperer;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.action.recuperer.injection.ViewRecupererEnSessionInjection;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entite.Entite;

public class ActionRecupererEnSession extends Action {

    public String variable;

    public ActionRecupererEnSession(Entite entite, Element element, String variable) {
        super(ActionType.NOUI, "recupererEnSession", entite, element);
        this.variable = variable;
        init();
    }

    public void overrideActionInjection() {
        viewActionInjection = new ViewRecupererEnSessionInjection(variable);
    }

}
