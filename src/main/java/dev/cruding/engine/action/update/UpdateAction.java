package dev.cruding.engine.action.update;

import dev.cruding.engine.action.specifique.BasicAction;
import dev.cruding.engine.action.update.injection.UpdateBusinessInjection;
import dev.cruding.engine.action.update.injection.UpdateCtrlInjection;
import dev.cruding.engine.action.update.injection.UpdateRepoInjection;
import dev.cruding.engine.action.update.injection.UpdateResourceInjection;
import dev.cruding.engine.action.update.injection.UpdateServiceInjection;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entity.Entity;

public class UpdateAction extends BasicAction {

    public UpdateAction(Entity entity, Element element) {
        super(ActionType.UCA, "maj", entity, element);
        icon("faEdit");
        confirm().byForm().lrest("put");
    }

    public void overrideActionInjection() {
        super.overrideActionInjection();
        resourceActionInjection = new UpdateResourceInjection();
        businessActionInjection = new UpdateBusinessInjection();
        serviceActionInjection = new UpdateServiceInjection();
        ctrlActionInjection = new UpdateCtrlInjection();
        repoActionInjection = new UpdateRepoInjection();

    }
}
