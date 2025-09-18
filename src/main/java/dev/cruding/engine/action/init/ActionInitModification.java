package dev.cruding.engine.action.init;

import dev.cruding.engine.action.init.injection.ViewInitModificationInjection;
import dev.cruding.engine.action.recuperer.ActionRecupererParChamp;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entite.Entite;

public class ActionInitModification extends ActionRecupererParChamp {


    public ActionInitModification(Entite entite, Element element) {
        super(entite, element);

    }

    public void overrideActionInjection() {
        super.overrideActionInjection();
        viewActionInjection = new ViewInitModificationInjection();
    }

}
