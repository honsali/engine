package dev.cruding.engine.action.update;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entity.Entity;

public class UpdateByDialogueAction extends Action {

    public UpdateByDialogueAction(Entity entity, Element element) {
        super(ActionType.MODAL, "modifier", entity, element);
        byForm().lrest("put");
    }
}
