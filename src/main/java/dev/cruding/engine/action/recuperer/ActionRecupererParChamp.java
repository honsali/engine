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

    public String lnameField;
    public String unameField;

    public ActionRecupererParChamp(Entite entite, Element element, String unameField) {
        super(ActionType.NOUI, "recupererPar" + unameField, entite, element);
        this.lnameField = StringUtils.uncapitalize(unameField);
        this.unameField = unameField;
        lcoreName("recupererPar" + unameField);
        lname("recuperer" + entite.uname + "Par" + unameField);
        init();
    }

    public void overrideActionInjection() {
        ctrlActionInjection = new CtrlRecupererInjection(lnameField, unameField);
        mdlActionInjection = new MdlRecupererInjection();
        resourceActionInjection = new ResourceRecupererInjection(lnameField, unameField);
        serviceActionInjection = new ServiceRecupererInjection(lnameField, unameField);
        viewActionInjection = new ViewRecupererInjection();
    }

}
