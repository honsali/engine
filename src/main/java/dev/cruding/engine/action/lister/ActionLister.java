package dev.cruding.engine.action.lister;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.action.lister.injection.BusinessListerInjection;
import dev.cruding.engine.action.lister.injection.CtrlListerInjection;
import dev.cruding.engine.action.lister.injection.MdlListerInjection;
import dev.cruding.engine.action.lister.injection.RepoListerInjection;
import dev.cruding.engine.action.lister.injection.ResourceListerInjection;
import dev.cruding.engine.action.lister.injection.ServiceListerInjection;
import dev.cruding.engine.action.lister.injection.ViewListerInjection;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entite.Entite;

public class ActionLister extends Action {

    public ActionLister(Entite entite, Element element) {
        super(ActionType.NOUI, "lister", entite, element);
    }

    public void overrideActionInjection() {
        String lcn = "";

        if (parIdPere && entite.havePere) {
            lcn = lcn + "ParId" + entite.upere;
        }
        lcoreName("lister");
        lnameAvecEntite("lister" + entite.uname + lcn);
        lnameSansEntite("lister" + lcn);

        ctrlActionInjection = new CtrlListerInjection();
        mdlActionInjection = new MdlListerInjection();
        repoActionInjection = new RepoListerInjection();
        resourceActionInjection = new ResourceListerInjection();
        businessActionInjection = new BusinessListerInjection();
        serviceActionInjection = new ServiceListerInjection();
        viewActionInjection = new ViewListerInjection();
    }
}
