package dev.cruding.engine.action.crud;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entite.Entite;

public class ActionModifierParDialogue extends Action {

    public ActionModifierParDialogue(Entite entite, Element element) {
        super(ActionType.DIALOGUE, "modifier", entite, element);
        parForm().lrest("put");
    }
}
