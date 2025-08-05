package dev.cruding.engine.action.changerPage;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.action.changerPage.injection.CtrlChangerPageFiltrerInjection;
import dev.cruding.engine.action.changerPage.injection.MdlChangerPageFiltrerInjection;
import dev.cruding.engine.action.changerPage.injection.ViewChangerPageFiltrerInjection;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entite.Entite;

public class ActionChangerPageFiltrer extends Action {

    public ActionChangerPageFiltrer(Entite entite, Element element) {
        super(ActionType.NOUI, "changerPageFiltrer", entite, element);
    }

    public void overrideActionInjection() {
        ctrlActionInjection = new CtrlChangerPageFiltrerInjection();
        mdlActionInjection = new MdlChangerPageFiltrerInjection();
        viewActionInjection = new ViewChangerPageFiltrerInjection();
    }


}
