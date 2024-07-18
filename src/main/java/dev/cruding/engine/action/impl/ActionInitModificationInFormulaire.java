package dev.cruding.engine.action.impl;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.component.entite.Formulaire;

public class ActionInitModificationInFormulaire extends Action {


    public ActionInitModificationInFormulaire(Formulaire formulaire) {
        super("initModification");
        inViewOnly = true;
    }

}
