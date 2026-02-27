package dev.cruding.engine.action.specifique;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.action.specifique.injection.BasicBusinessInjection;
import dev.cruding.engine.action.specifique.injection.BasicCtrlInjection;
import dev.cruding.engine.action.specifique.injection.BasicMdlInjection;
import dev.cruding.engine.action.specifique.injection.BasicRepoInjection;
import dev.cruding.engine.action.specifique.injection.BasicResourceInjection;
import dev.cruding.engine.action.specifique.injection.BasicServiceInjection;
import dev.cruding.engine.action.specifique.injection.BasicViewInjection;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entity.Entity;

public class BasicAction extends Action {

    public BasicAction(ActionType type, String lcoreName, Entity entity, Element element) {
        super(type, lcoreName, entity, element);
    }

    public void overrideActionInjection() {
        viewActionInjection = new BasicViewInjection();
        mdlActionInjection = new BasicMdlInjection();
        ctrlActionInjection = new BasicCtrlInjection();
        serviceActionInjection = new BasicServiceInjection();
        resourceActionInjection = new BasicResourceInjection();
        businessActionInjection = new BasicBusinessInjection();
        repoActionInjection = new BasicRepoInjection();
    }

}
