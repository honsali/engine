package dev.cruding.engine.action.creer;

import dev.cruding.engine.action.creer.injection.BusinessCreerInjection;
import dev.cruding.engine.action.creer.injection.CtrlCreerInjection;
import dev.cruding.engine.action.creer.injection.MdlCreerInjection;
import dev.cruding.engine.action.creer.injection.ResourceCreerInjection;
import dev.cruding.engine.action.creer.injection.ServiceCreerInjection;
import dev.cruding.engine.action.creer.injection.ViewCreerInjection;
import dev.cruding.engine.action.specifique.ActionSpecifique;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entite.Entite;
import dev.cruding.engine.injection.RepoActionInjection;

public class ActionCreer extends ActionSpecifique {

    public ActionCreer(Entite entite, Element element) {
        super(ActionType.UCA, "creer", entite, element);
        icone("faAdd");
        confirmer();
    }

    public void overrideActionInjection() {
        viewActionInjection = new ViewCreerInjection();
        mdlActionInjection = new MdlCreerInjection();
        ctrlActionInjection = new CtrlCreerInjection();
        resourceActionInjection = new ResourceCreerInjection();
        serviceActionInjection = new ServiceCreerInjection();
        businessActionInjection = new BusinessCreerInjection();
        repoActionInjection = new RepoActionInjection();

    }


}
