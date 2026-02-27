package dev.cruding.engine.action.init;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.action.init.injection.CtrlInitFilterInjection;
import dev.cruding.engine.action.init.injection.MdlInitFilterInjection;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entity.Entity;

public class InitFilterAction extends Action {

    private boolean filterOnLoad = false;

    public InitFilterAction(Entity entity, Element element, boolean paginated) {
        super(ActionType.NOUI, "initialiserFiltrer", entity, element);
        this.paginated = paginated;
    }

    public InitFilterAction filterOnLoad() {
        this.filterOnLoad = true;
        return this;
    }


    public void overrideActionInjection() {
        ctrlActionInjection = new CtrlInitFilterInjection(filterOnLoad);
        mdlActionInjection = new MdlInitFilterInjection();
    }
}
