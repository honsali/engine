package dev.cruding.engine.action.changePage;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.action.changePage.injection.ChangePageForFilterCtrlInjection;
import dev.cruding.engine.action.changePage.injection.ChangePageMdlInjection;
import dev.cruding.engine.action.changePage.injection.ChangePageViewInjection;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entity.Entity;

public class ChangePageForFilterAction extends Action {

    public ChangePageForFilterAction(Entity entity, Element element) {
        super(ActionType.NOUI, "changerPageFiltrer", entity, element);
    }

    public void overrideActionInjection() {
        ctrlActionInjection = new ChangePageForFilterCtrlInjection();
        mdlActionInjection = new ChangePageMdlInjection();
        viewActionInjection = new ChangePageViewInjection();
    }


}
