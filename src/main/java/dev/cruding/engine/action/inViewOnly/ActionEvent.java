package dev.cruding.engine.action.inViewOnly;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.action.inViewOnly.injection.ViewEventInjection;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entite.Entite;

public class ActionEvent extends Action {


    private String targetEvent;

    public ActionEvent(Entite entite, Element element, String targetEvent) {
        super(ActionType.NOUI, targetEvent, entite, element);
        this.targetEvent = targetEvent;
        lname(targetEvent);
        inViewOnly();
        init();
    }

    public void overrideActionInjection() {
        viewActionInjection = new ViewEventInjection(targetEvent);
    }
}
