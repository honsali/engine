package dev.cruding.engine.action.find;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.action.find.injection.InitFindCtrlInjection;
import dev.cruding.engine.action.find.injection.InitFindMdlInjection;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entity.Entity;

public class InitFindAction extends Action {


    public InitFindAction(Entity entity, Element element) {
        super(ActionType.NOUI, "initialiserChercher", entity, element);
    }

    public void overrideActionInjection() {
        ctrlActionInjection = new InitFindCtrlInjection();
        mdlActionInjection = new InitFindMdlInjection();
    }
}
