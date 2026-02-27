package dev.cruding.engine.action.init;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.action.init.injection.CtrlInitUpdateInjection;
import dev.cruding.engine.action.init.injection.MdlInitUpdateInjection;
import dev.cruding.engine.action.init.injection.ViewInitUpdateInjection;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entity.Entity;

public class InitUpdateAction extends Action {

    private static final String type = "initModification";
    private Action[] actionList = new Action[0];

    public InitUpdateAction(Entity entity, Element element) {
        this(entity, element, new Action[0]);
    }


    public InitUpdateAction(Entity entity, Element element, Action... actionList) {
        super(ActionType.NORMAL, type, entity, element);
        this.actionList = actionList;
        for (Action action : actionList) {
            action.inViewOnly().type(ActionType.FLOW);
        }
    }

    public void overrideActionInjection() {
        super.overrideActionInjection();
        viewActionInjection = new ViewInitUpdateInjection();
        ctrlActionInjection = new CtrlInitUpdateInjection(actionList);
        mdlActionInjection = new MdlInitUpdateInjection(actionList);
    }

}
