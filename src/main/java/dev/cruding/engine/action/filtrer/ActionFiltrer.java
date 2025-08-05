package dev.cruding.engine.action.filtrer;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.action.changerPage.ActionChangerPageFiltrer;
import dev.cruding.engine.action.filtrer.injection.CtrlFiltrerInjection;
import dev.cruding.engine.action.filtrer.injection.MdlFiltrerInjection;
import dev.cruding.engine.action.filtrer.injection.MdlFiltrerPagineInjection;
import dev.cruding.engine.action.filtrer.injection.RepoFiltrerInjection;
import dev.cruding.engine.action.filtrer.injection.ResourceFiltrerInjection;
import dev.cruding.engine.action.filtrer.injection.ServiceFiltrerInjection;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entite.Entite;
import dev.cruding.engine.injection.ViewActionInjection;

public class ActionFiltrer extends Action {

    private ActionInitialiserFiltrer actionInitialiserFiltrer;

    public ActionFiltrer(Entite entite, Element element, boolean pagine) {
        super(ActionType.NOUI, "filtrer", entite, element);
        parForm();
        if (pagine) {
            actionPagination(new ActionChangerPageFiltrer(entite, element));
        }
        actionInitialiserFiltrer = new ActionInitialiserFiltrer(entite, element, pagine);

    }

    public ActionFiltrer filtrerAuDepart() {
        this.actionInitialiserFiltrer.filtrerAuDepart();
        return this;
    }

    public void overrideActionInjection() {
        ctrlActionInjection = new CtrlFiltrerInjection();
        if (actionPagination == null) {
            mdlActionInjection = new MdlFiltrerInjection();
        } else {
            mdlActionInjection = new MdlFiltrerPagineInjection();
        }
        repoActionInjection = new RepoFiltrerInjection();
        resourceActionInjection = new ResourceFiltrerInjection();
        serviceActionInjection = new ServiceFiltrerInjection();
        viewActionInjection = new ViewActionInjection();
    }
}
