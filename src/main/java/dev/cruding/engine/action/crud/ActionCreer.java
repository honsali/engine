package dev.cruding.engine.action.crud;

import dev.cruding.engine.action.crud.injection.ResourceCreerInjection;
import dev.cruding.engine.action.specifique.ActionSpecifique;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entite.Entite;

public class ActionCreer extends ActionSpecifique {

    public ActionCreer(Entite entite, Element element) {
        super(ActionType.UCA, "creer", entite, element);
        confirmer().byForm().resultatInId().lrest("post");
        init();
    }

    public void overrideActionInjection() {

        resourceActionInjection = new ResourceCreerInjection();

    }


}
