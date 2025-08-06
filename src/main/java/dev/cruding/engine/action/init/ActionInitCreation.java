package dev.cruding.engine.action.init;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.action.init.injection.CtrlInitCreationInjection;
import dev.cruding.engine.action.init.injection.MdlInitCreationInjection;
import dev.cruding.engine.action.init.injection.ViewInitCreationInjection;
import dev.cruding.engine.champ.Champ;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entite.Entite;

public class ActionInitCreation extends Action {

    private static final String type = "initCreation";
    private Champ[] listeChamp = new Champ[0];

    public ActionInitCreation(Entite entite, Element element) {
        this(entite, element, new Champ[0]);
    }


    public ActionInitCreation(Entite entite, Element element, Champ... listeChamp) {
        super(ActionType.NOUI, type, entite, element);
        this.listeChamp = listeChamp;
    }

    public void overrideActionInjection() {
        viewActionInjection = new ViewInitCreationInjection();
        ctrlActionInjection = new CtrlInitCreationInjection(listeChamp);
        mdlActionInjection = new MdlInitCreationInjection(listeChamp);
    }


}
