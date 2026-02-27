package dev.cruding.engine.action.find;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.action.find.injection.FindCtrlInjection;
import dev.cruding.engine.action.find.injection.FindMdlInjection;
import dev.cruding.engine.action.find.injection.FindRepoInjection;
import dev.cruding.engine.action.find.injection.FindResourceInjection;
import dev.cruding.engine.action.find.injection.FindServiceInjection;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.injection.ActionViewInjection;

public class FindAction extends Action {


    public FindAction(Entity entity, Element element) {
        super(ActionType.NOUI, "chercher", entity, element);
        new InitFindAction(entity, element);

    }

    public void overrideActionInjection() {
        ctrlActionInjection = new FindCtrlInjection();
        mdlActionInjection = new FindMdlInjection();
        repoActionInjection = new FindRepoInjection();
        resourceActionInjection = new FindResourceInjection();
        serviceActionInjection = new FindServiceInjection();
        viewActionInjection = new ActionViewInjection();
    }
}
