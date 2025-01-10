package dev.cruding.engine.action.lister;

import dev.cruding.engine.action.Action;
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
        init();
    }

    public void overrideActionInjection() {
        ctrlActionInjection = new CtrlListerInjection();
        mdlActionInjection = new MdlListerInjection();
        repoActionInjection = new RepoListerInjection();
        resourceActionInjection = new ResourceListerInjection();
        serviceActionInjection = new ServiceListerInjection();
        viewActionInjection = new ViewListerInjection();
    }
}