package dev.cruding.engine.action.inViewOnly;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.action.inViewOnly.injection.MdlInitialiserMdl;
import dev.cruding.engine.action.inViewOnly.injection.ViewInitialiserMdl;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entite.Entite;

public class ActionInitialiserMdl extends Action {

    public ActionInitialiserMdl(Entite entite, Element element) {
        super(ActionType.NORMALE, "initialiser", entite, element);
        inViewOnly();
    }

    public void overrideActionInjection() {
        viewActionInjection = new ViewInitialiserMdl();
        mdlActionInjection = new MdlInitialiserMdl();
    }
}
