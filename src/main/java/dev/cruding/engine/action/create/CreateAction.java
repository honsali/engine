package dev.cruding.engine.action.create;

import dev.cruding.engine.action.create.injection.CreateBusinessInjection;
import dev.cruding.engine.action.create.injection.CreateCtrlInjection;
import dev.cruding.engine.action.create.injection.CreateMdlInjection;
import dev.cruding.engine.action.create.injection.CreateRepoInjection;
import dev.cruding.engine.action.create.injection.CreateResourceInjection;
import dev.cruding.engine.action.create.injection.CreateServiceInjection;
import dev.cruding.engine.action.create.injection.CreateViewInjection;
import dev.cruding.engine.action.specifique.BasicAction;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entity.Entity;

public class CreateAction extends BasicAction {

    public CreateAction(Entity entity, Element element) {
        super(ActionType.UCA, "creer", entity, element);
        icon("faAdd");
        confirm();
    }

    public void overrideActionInjection() {
        viewActionInjection = new CreateViewInjection();
        mdlActionInjection = new CreateMdlInjection();
        ctrlActionInjection = new CreateCtrlInjection();
        resourceActionInjection = new CreateResourceInjection();
        serviceActionInjection = new CreateServiceInjection();
        businessActionInjection = new CreateBusinessInjection();
        repoActionInjection = new CreateRepoInjection();

    }


}
