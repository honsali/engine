package dev.cruding.engine.action.inViewOnly;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.action.inViewOnly.injection.ViewGoToModuleInjection;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entite.Entite;

public class ActionGoToModule extends Action {

    private String targetModule;

    public ActionGoToModule(Entite entite, Element element, String targetModule) {
        super(ActionType.NOUI, "goToModule", entite, element);
        this.targetModule = targetModule;
        inViewOnly();
        lcoreName("goToModule" + targetModule);
        lname("goToModule" + targetModule);
    }

    public void init() {

        viewActionInjection = new ViewGoToModuleInjection(targetModule);
    }
}
