package dev.cruding.engine.action.impl;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.component.entite.Formulaire;

public class ActionInitCreationInFormulaire extends Action {


    public ActionInitCreationInFormulaire(Formulaire formulaire) {
        super("initCreation");
        inViewOnly = true;
    }

}
