package dev.cruding.engine.action.init;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.action.init.injection.CtrlInitCreateInjection;
import dev.cruding.engine.action.init.injection.MdlInitCreateInjection;
import dev.cruding.engine.action.init.injection.ViewInitCreateInjection;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.field.Field;

public class InitCreateAction extends Action {

    private static final String type = "initCreation";
    private Field[] fieldList = new Field[0];

    public InitCreateAction(Entity entity, Element element) {
        this(entity, element, new Field[0]);
    }


    public InitCreateAction(Entity entity, Element element, Field... fieldList) {
        super(ActionType.NOUI, type, entity, element);
        this.fieldList = fieldList;
    }

    public void overrideActionInjection() {
        viewActionInjection = new ViewInitCreateInjection();
        ctrlActionInjection = new CtrlInitCreateInjection(fieldList);
        mdlActionInjection = new MdlInitCreateInjection(fieldList);
    }


}
