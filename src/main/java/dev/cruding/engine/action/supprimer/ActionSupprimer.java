package dev.cruding.engine.action.supprimer;

import dev.cruding.engine.action.specifique.ActionSpecifique;
import dev.cruding.engine.action.supprimer.injection.BusinessSupprimerInjection;
import dev.cruding.engine.action.supprimer.injection.CtrlSuppressionInjection;
import dev.cruding.engine.action.supprimer.injection.ResourceSupprimerInjection;
import dev.cruding.engine.action.supprimer.injection.ServiceSupprimerInjection;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entite.Entite;

public class ActionSupprimer extends ActionSpecifique {


    public ActionSupprimer(Entite entite, Element element) {
        super(ActionType.UCA, "supprimer", entite, element);
        confirmer().lrest("delete");
    }

    public void overrideActionInjection() {
        super.overrideActionInjection();
        resourceActionInjection = new ResourceSupprimerInjection();
        businessActionInjection = new BusinessSupprimerInjection();
        serviceActionInjection = new ServiceSupprimerInjection();
        ctrlActionInjection = new CtrlSuppressionInjection();
    }
}
