package dev.cruding.engine.action.impl;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entite.Entite;

public class ActionChangerSelection extends Action {

    public ActionChangerSelection(ActionType type, String lcoreName, Entite entite, Element element) {
        super(ActionType.NOUI, "changerSelection", entite, element);
        inViewOnly();
    }

}
