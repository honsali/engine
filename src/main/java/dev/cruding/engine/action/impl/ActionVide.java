package dev.cruding.engine.action.impl;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entite.Entite;

public class ActionVide extends Action {

    public ActionVide(ActionType type, String lcoreName, Entite entite, Element element) {
        super(type, lcoreName, entite, element);
        init();
    }


}
