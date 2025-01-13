package dev.cruding.engine.action.changerPage;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.action.changerPage.injection.CtrlChangerPageListerInjection;
import dev.cruding.engine.action.changerPage.injection.MdlChangerPageChercherInjection;
import dev.cruding.engine.action.changerPage.injection.ViewChangerPageChercherInjection;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entite.Entite;

public class ActionChangerPageLister extends Action {

    public ActionChangerPageLister(Entite entite, Element element) {
        super(ActionType.NOUI, "changerPageLister", entite, element);
    }

    public void overrideActionInjection() {
        ctrlActionInjection = new CtrlChangerPageListerInjection();
        mdlActionInjection = new MdlChangerPageChercherInjection();
        viewActionInjection = new ViewChangerPageChercherInjection();
    }


}
