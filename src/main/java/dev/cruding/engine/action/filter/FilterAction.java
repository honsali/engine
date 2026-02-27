package dev.cruding.engine.action.filter;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.action.changePage.ChangePageForFilterAction;
import dev.cruding.engine.action.filter.injection.BusinessFilterInjection;
import dev.cruding.engine.action.filter.injection.CtrlFilterInjection;
import dev.cruding.engine.action.filter.injection.MdlFilterInjection;
import dev.cruding.engine.action.filter.injection.MdlFilterPaginatedInjection;
import dev.cruding.engine.action.filter.injection.RepoFilterInjection;
import dev.cruding.engine.action.filter.injection.ResourceFilterInjection;
import dev.cruding.engine.action.filter.injection.ServiceFilterInjection;
import dev.cruding.engine.action.init.InitFilterAction;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.injection.ActionViewInjection;

public class FilterAction extends Action {

    private InitFilterAction initFilterAction;

    public FilterAction(Entity entity, Element element, boolean paginated) {
        super(ActionType.NOUI, "filtrer", entity, element);
        byForm();
        if (paginated) {
            paginationAction(new ChangePageForFilterAction(entity, element));
        }
        initFilterAction = new InitFilterAction(entity, element, paginated);

    }

    public FilterAction filterOnLoad() {
        this.initFilterAction.filterOnLoad();
        return this;
    }

    public void overrideActionInjection() {
        ctrlActionInjection = new CtrlFilterInjection();
        if (paginationAction == null) {
            mdlActionInjection = new MdlFilterInjection();
        } else {
            mdlActionInjection = new MdlFilterPaginatedInjection();
        }
        repoActionInjection = new RepoFilterInjection();
        resourceActionInjection = new ResourceFilterInjection();
        businessActionInjection = new BusinessFilterInjection();
        serviceActionInjection = new ServiceFilterInjection();
        viewActionInjection = new ActionViewInjection();
    }
}
