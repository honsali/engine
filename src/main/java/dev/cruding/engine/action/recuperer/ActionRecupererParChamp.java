package dev.cruding.engine.action.recuperer;

import org.apache.commons.lang3.StringUtils;
import dev.cruding.engine.action.Action;
import dev.cruding.engine.action.recuperer.injection.CtrlRecupererInjection;
import dev.cruding.engine.action.recuperer.injection.MdlRecupererInjection;
import dev.cruding.engine.action.recuperer.injection.ResourceRecupererInjection;
import dev.cruding.engine.action.recuperer.injection.ServiceRecupererInjection;
import dev.cruding.engine.action.recuperer.injection.ViewRecupererInjection;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entite.Entite;

public class ActionRecupererParChamp extends Action {

    public String lnameChamp;
    public String unameChamp;

    public ActionRecupererParChamp(Entite entite, Element element, String unameChamp) {
        super(ActionType.NOUI, "recupererPar" + unameChamp, entite, element);
        this.lnameChamp = StringUtils.uncapitalize(unameChamp);
        this.unameChamp = unameChamp;
        lcoreName("recuperer");
        lnameAvecEntite("recuperer" + entite.uname + "Par" + unameChamp);
        lnameSansEntite("recupererPar" + unameChamp);
    }

    public void overrideActionInjection() {
        ctrlActionInjection = new CtrlRecupererInjection(lnameChamp, unameChamp);
        mdlActionInjection = new MdlRecupererInjection();
        resourceActionInjection = new ResourceRecupererInjection(lnameChamp, unameChamp);
        serviceActionInjection = new ServiceRecupererInjection(lnameChamp, unameChamp);
        viewActionInjection = new ViewRecupererInjection();
    }

}
