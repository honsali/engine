package dev.cruding.engine.action.init;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.action.init.injection.CtrlInitEditionInjection;
import dev.cruding.engine.action.init.injection.MdlInitEditionInjection;
import dev.cruding.engine.action.init.injection.ViewInitEditionInjection;
import dev.cruding.engine.champ.Champ;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entite.Entite;

public class ActionInitCreation extends Action {

    private static final String type = "initCreation";
    private Champ[] fieldList = new Champ[0];

    public ActionInitCreation(Entite entite, Element element) {
        this(entite, element, new Champ[0]);
    }


    public ActionInitCreation(Entite entite, Element element, Champ... fieldList) {
        super(ActionType.NOUI, type, entite, element);
        this.fieldList = fieldList;
    }

    public void overrideActionInjection() {
        viewActionInjection = new ViewInitEditionInjection(type);
        ctrlActionInjection = new CtrlInitEditionInjection(fieldList);
        mdlActionInjection = new MdlInitEditionInjection(fieldList);
    }


}
