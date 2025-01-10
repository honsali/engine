package dev.cruding.engine.action.specifique;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.action.specifique.injection.CtrlSpecifiqueInjection;
import dev.cruding.engine.action.specifique.injection.MdlSpecifiqueInjection;
import dev.cruding.engine.action.specifique.injection.RepoSpecifiqueInjection;
import dev.cruding.engine.action.specifique.injection.ResourceSpecifiqueInjection;
import dev.cruding.engine.action.specifique.injection.ServiceSpecifiqueInjection;
import dev.cruding.engine.action.specifique.injection.ViewSpecifiqueInjection;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entite.Entite;

public class ActionSpecifique extends Action {

    public ActionSpecifique(ActionType type, String lcoreName, Entite entite, Element element) {
        super(type, lcoreName, entite, element);
        init();
    }

    public void overrideActionInjection() {
        ctrlActionInjection = new CtrlSpecifiqueInjection();
        mdlActionInjection = new MdlSpecifiqueInjection();
        repoActionInjection = new RepoSpecifiqueInjection();
        resourceActionInjection = new ResourceSpecifiqueInjection();
        serviceActionInjection = new ServiceSpecifiqueInjection();
        viewActionInjection = new ViewSpecifiqueInjection();
    }

}
