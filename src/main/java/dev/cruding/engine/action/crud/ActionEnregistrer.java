package dev.cruding.engine.action.crud;

import dev.cruding.engine.action.crud.injection.ResourceEnregistrerInjection;
import dev.cruding.engine.action.specifique.ActionSpecifique;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entite.Entite;

public class ActionEnregistrer extends ActionSpecifique {

    public ActionEnregistrer(Entite entite, Element element) {
        super(ActionType.UCA, "enregistrer", entite, element);
        icone("faEdit");
        confirmer().parForm().lrest("put");
    }

    public void overrideActionInjection() {
        super.overrideActionInjection();
        resourceActionInjection = new ResourceEnregistrerInjection();
    }



}
