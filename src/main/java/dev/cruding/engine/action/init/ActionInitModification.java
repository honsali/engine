package dev.cruding.engine.action.init;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.action.init.injection.CtrlInitModificationInjection;
import dev.cruding.engine.action.init.injection.MdlInitModificationInjection;
import dev.cruding.engine.action.init.injection.ViewInitEditionInjection;
import dev.cruding.engine.champ.Champ;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entite.Entite;

public class ActionInitModification extends Action {

    private static final String type = "initModification";
    private Champ[] fieldList = new Champ[0];

    public ActionInitModification(Entite entite, Element element) {
        this(entite, element, new Champ[0]);
    }


    public ActionInitModification(Entite entite, Element element, Champ... fieldList) {
        super(ActionType.NOUI, type, entite, element);
        this.fieldList = fieldList;
    }

    public void overrideActionInjection() {
        viewActionInjection = new ViewInitEditionInjection(type);
        ctrlActionInjection = new CtrlInitModificationInjection(fieldList);
        mdlActionInjection = new MdlInitModificationInjection(fieldList);
    }

}
