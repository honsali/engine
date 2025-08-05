package dev.cruding.engine.action.filtrer;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.action.filtrer.injection.CtrlInitialiserFiltrerInjection;
import dev.cruding.engine.action.filtrer.injection.MdlInitialiserFiltrerInjection;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entite.Entite;

public class ActionInitialiserFiltrer extends Action {

    private boolean filtrerAuDepart = false;

    public ActionInitialiserFiltrer(Entite entite, Element element, boolean pagine) {
        super(ActionType.NOUI, "initialiserFiltrer", entite, element);
        this.pagine = pagine;
    }

    public ActionInitialiserFiltrer filtrerAuDepart() {
        this.filtrerAuDepart = true;
        return this;
    }


    public void overrideActionInjection() {
        ctrlActionInjection = new CtrlInitialiserFiltrerInjection(filtrerAuDepart);
        mdlActionInjection = new MdlInitialiserFiltrerInjection();
    }
}
