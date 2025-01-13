package dev.cruding.engine.action.changerPage;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.action.changerPage.injection.CtrlChangerPageChercherInjection;
import dev.cruding.engine.action.changerPage.injection.MdlChangerPageChercherInjection;
import dev.cruding.engine.action.changerPage.injection.ViewChangerPageChercherInjection;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entite.Entite;

public class ActionChangerPageChercher extends Action {

    public ActionChangerPageChercher(Entite entite, Element element) {
        super(ActionType.NOUI, "changerPageChercher", entite, element);
    }

    public void overrideActionInjection() {
        ctrlActionInjection = new CtrlChangerPageChercherInjection();
        mdlActionInjection = new MdlChangerPageChercherInjection();
        viewActionInjection = new ViewChangerPageChercherInjection();
    }


}
