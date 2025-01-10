package dev.cruding.engine.action.listerEnPage;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.action.lister.injection.ViewListerInjection;
import dev.cruding.engine.action.listerEnPage.injection.CtrlListerEnPageInjection;
import dev.cruding.engine.action.listerEnPage.injection.MdlListerEnPageInjection;
import dev.cruding.engine.action.listerEnPage.injection.RepoListerEnPageInjection;
import dev.cruding.engine.action.listerEnPage.injection.ResourceListerEnPageInjection;
import dev.cruding.engine.action.listerEnPage.injection.ServiceListerEnPageInjection;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entite.Entite;

public class ActionListerEnPage extends Action {

    public ActionListerEnPage(Entite entite, Element element) {
        super(ActionType.NOUI, "listerEnPage", entite, element);
        init();
    }

    public void overrideActionInjection() {
        ctrlActionInjection = new CtrlListerEnPageInjection();
        mdlActionInjection = new MdlListerEnPageInjection();
        repoActionInjection = new RepoListerEnPageInjection();
        resourceActionInjection = new ResourceListerEnPageInjection();
        serviceActionInjection = new ServiceListerEnPageInjection();
        viewActionInjection = new ViewListerInjection();
    }
}
