package dev.cruding.engine.action.chercher;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.action.chercher.injection.CtrlChercherInjection;
import dev.cruding.engine.action.chercher.injection.MdlChercherInjection;
import dev.cruding.engine.action.chercher.injection.RepoChercherInjection;
import dev.cruding.engine.action.chercher.injection.ResourceChercherInjection;
import dev.cruding.engine.action.chercher.injection.ServiceChercherInjection;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entite.Entite;
import dev.cruding.engine.injection.ViewActionInjection;

public class ActionChercher extends Action {


    public ActionChercher(Entite entite, Element element) {
        super(ActionType.NOUI, "chercher", entite, element);
        new ActionInitialiserChercher(entite, element);

    }

    public void overrideActionInjection() {
        ctrlActionInjection = new CtrlChercherInjection();
        mdlActionInjection = new MdlChercherInjection();
        repoActionInjection = new RepoChercherInjection();
        resourceActionInjection = new ResourceChercherInjection();
        serviceActionInjection = new ServiceChercherInjection();
        viewActionInjection = new ViewActionInjection();
    }
}
