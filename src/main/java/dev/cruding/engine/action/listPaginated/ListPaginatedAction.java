package dev.cruding.engine.action.listPaginated;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.action.changePage.ChangePageForListAction;
import dev.cruding.engine.action.list.injection.ListViewInjection;
import dev.cruding.engine.action.listPaginated.injection.ListPaginatedCtrlInjection;
import dev.cruding.engine.action.listPaginated.injection.ListPaginatedMdlInjection;
import dev.cruding.engine.action.listPaginated.injection.ListPaginatedRepoInjection;
import dev.cruding.engine.action.listPaginated.injection.ListPaginatedResourceInjection;
import dev.cruding.engine.action.listPaginated.injection.ListPaginatedServiceInjection;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entity.Entity;

public class ListPaginatedAction extends Action {

    public ListPaginatedAction(Entity entity, Element element) {
        super(ActionType.NOUI, "listerEnPage", entity, element);
        paginationAction(new ChangePageForListAction(entity, element));
    }

    public void overrideActionInjection() {
        ctrlActionInjection = new ListPaginatedCtrlInjection();
        mdlActionInjection = new ListPaginatedMdlInjection();
        repoActionInjection = new ListPaginatedRepoInjection();
        resourceActionInjection = new ListPaginatedResourceInjection();
        serviceActionInjection = new ListPaginatedServiceInjection();
        viewActionInjection = new ListViewInjection();
    }
}
