package dev.cruding.engine.action.delete;

import dev.cruding.engine.action.delete.injection.DeleteBusinessInjection;
import dev.cruding.engine.action.delete.injection.DeleteCtrlInjection;
import dev.cruding.engine.action.delete.injection.DeleteMdlInjection;
import dev.cruding.engine.action.delete.injection.DeleteResourceInjection;
import dev.cruding.engine.action.delete.injection.DeleteServiceInjection;
import dev.cruding.engine.action.specifique.BasicAction;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entity.Entity;

public class DeleteAction extends BasicAction {


    public DeleteAction(Entity entity, Element element) {
        super(ActionType.UCA, "supprimer", entity, element);
        confirm().lrest("delete");
    }

    public void overrideActionInjection() {
        super.overrideActionInjection();
        resourceActionInjection = new DeleteResourceInjection();
        businessActionInjection = new DeleteBusinessInjection();
        serviceActionInjection = new DeleteServiceInjection();
        ctrlActionInjection = new DeleteCtrlInjection();
        mdlActionInjection = new DeleteMdlInjection();
    }
}
