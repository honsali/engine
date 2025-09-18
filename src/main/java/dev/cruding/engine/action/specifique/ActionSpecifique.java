package dev.cruding.engine.action.specifique;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.action.specifique.injection.BusinessSpecifiqueInjection;
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
    }

    public void overrideActionInjection() {
        viewActionInjection = new ViewSpecifiqueInjection();
        mdlActionInjection = new MdlSpecifiqueInjection();
        ctrlActionInjection = new CtrlSpecifiqueInjection();
        serviceActionInjection = new ServiceSpecifiqueInjection();
        resourceActionInjection = new ResourceSpecifiqueInjection();
        businessActionInjection = new BusinessSpecifiqueInjection();
        repoActionInjection = new RepoSpecifiqueInjection();
    }

}
