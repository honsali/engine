package dev.cruding.engine.action.init;

import org.apache.commons.lang3.StringUtils;
import dev.cruding.engine.action.init.injection.ViewInitModificationInjection;
import dev.cruding.engine.action.recuperer.ActionRecupererParChamp;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entite.Entite;

public class ActionInitModification extends ActionRecupererParChamp {

    public String lnameChamp;
    public String unameChamp;

    public ActionInitModification(Entite entite, Element element, String unameChamp) {
        super(entite, element, unameChamp);
        this.lnameChamp = StringUtils.uncapitalize(unameChamp);
        this.unameChamp = unameChamp;
    }

    public void overrideActionInjection() {
        super.overrideActionInjection();
        viewActionInjection = new ViewInitModificationInjection(lnameChamp, unameChamp);
    }

}
