package dev.cruding.engine.action.crud;

import dev.cruding.engine.action.crud.injection.ResourceSupprimerInjection;
import dev.cruding.engine.action.specifique.ActionSpecifique;
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
    }

}
