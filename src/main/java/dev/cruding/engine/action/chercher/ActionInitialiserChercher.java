package dev.cruding.engine.action.chercher;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.action.chercher.injection.CtrlInitialiserChercherInjection;
import dev.cruding.engine.action.chercher.injection.MdlInitialiserChercherInjection;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entite.Entite;

public class ActionInitialiserChercher extends Action {


    public ActionInitialiserChercher(Entite entite, Element element) {
        super(ActionType.NOUI, "initialiserChercher", entite, element);
    }

    public void overrideActionInjection() {
        ctrlActionInjection = new CtrlInitialiserChercherInjection();
        mdlActionInjection = new MdlInitialiserChercherInjection();
    }
}
