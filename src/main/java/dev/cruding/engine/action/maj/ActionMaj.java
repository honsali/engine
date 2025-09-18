package dev.cruding.engine.action.maj;

import dev.cruding.engine.action.maj.injection.BusinessMajInjection;
import dev.cruding.engine.action.maj.injection.CtrlMajInjection;
import dev.cruding.engine.action.maj.injection.ResourceMajInjection;
import dev.cruding.engine.action.maj.injection.ServiceMajInjection;
import dev.cruding.engine.action.specifique.ActionSpecifique;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entite.Entite;

public class ActionMaj extends ActionSpecifique {

    public ActionMaj(Entite entite, Element element) {
        super(ActionType.UCA, "maj", entite, element);
        icone("faEdit");
        confirmer().parForm().lrest("put");
    }

    public void overrideActionInjection() {
        super.overrideActionInjection();
        resourceActionInjection = new ResourceMajInjection();
        businessActionInjection = new BusinessMajInjection();
        serviceActionInjection = new ServiceMajInjection();
        ctrlActionInjection = new CtrlMajInjection();
    }
}
