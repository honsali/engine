package dev.cruding.engine.action.rechargerPage;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.action.rechargerPage.injection.CtrlRechargerPageFiltrerInjection;
import dev.cruding.engine.action.rechargerPage.injection.MdlRechargerPageFiltrerInjection;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entite.Entite;

public class ActionRechargerPageFiltrer extends Action {

    public ActionRechargerPageFiltrer(Entite entite, Element element) {
        super(ActionType.NOUI, "rechargerPageFiltrer", entite, element);
    }

    public void overrideActionInjection() {
        ctrlActionInjection = new CtrlRechargerPageFiltrerInjection();
        mdlActionInjection = new MdlRechargerPageFiltrerInjection();
    }


}
