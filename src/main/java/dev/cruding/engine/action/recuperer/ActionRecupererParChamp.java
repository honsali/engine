package dev.cruding.engine.action.recuperer;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.action.recuperer.injection.BusinessRecupererInjection;
import dev.cruding.engine.action.recuperer.injection.CtrlRecupererParChampInjection;
import dev.cruding.engine.action.recuperer.injection.MdlRecupererInjectionParChamp;
import dev.cruding.engine.action.recuperer.injection.ResourceRecupererInjection;
import dev.cruding.engine.action.recuperer.injection.ServiceRecupererInjection;
import dev.cruding.engine.action.recuperer.injection.ViewRecupererParChampInjection;
import dev.cruding.engine.champ.Champ;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entite.Entite;

public class ActionRecupererParChamp extends Action {


    public ActionRecupererParChamp(Entite entite, Element element) {
        super(ActionType.NOUI, "recuperer", entite, element);
    }

    public void overrideActionInjection() {

        String lcn = "";
        Champ[] listeChamps = parChamp;
        if (listeChamps != null) {
            for (Champ c : listeChamps) {
                if (c.isPere || c.isRef) {
                    lcn = lcn + "ParId" + c.uname;
                } else {
                    lcn = lcn + "Par" + c.uname;
                }
            }
        }
        lcoreName("recuperer");
        lnameAvecEntite("recuperer" + entite.uname + lcn);
        lnameSansEntite("recuperer" + lcn);

        ctrlActionInjection = new CtrlRecupererParChampInjection();
        mdlActionInjection = new MdlRecupererInjectionParChamp();
        resourceActionInjection = new ResourceRecupererInjection();
        businessActionInjection = new BusinessRecupererInjection();
        serviceActionInjection = new ServiceRecupererInjection();
        viewActionInjection = new ViewRecupererParChampInjection();

    }
}
