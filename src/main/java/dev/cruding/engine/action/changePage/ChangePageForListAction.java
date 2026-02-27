package dev.cruding.engine.action.changePage;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.action.changePage.injection.ChangePageForListCtrlInjection;
import dev.cruding.engine.action.changePage.injection.ChangePageMdlInjection;
import dev.cruding.engine.action.changePage.injection.ChangePageViewInjection;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entity.Entity;

public class ChangePageForListAction extends Action {

    public ChangePageForListAction(Entity entity, Element element) {
        super(ActionType.NOUI, "changerPageLister", entity, element);
    }

    public void overrideActionInjection() {
        ctrlActionInjection = new ChangePageForListCtrlInjection();
        mdlActionInjection = new ChangePageMdlInjection();
        viewActionInjection = new ChangePageViewInjection();
    }


}
