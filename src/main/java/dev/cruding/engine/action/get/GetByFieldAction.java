package dev.cruding.engine.action.get;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.action.get.injection.GetBusinessInjection;
import dev.cruding.engine.action.get.injection.GetByFieldCtrlInjection;
import dev.cruding.engine.action.get.injection.GetByFieldMdlInjection;
import dev.cruding.engine.action.get.injection.GetByFieldResourceInjection;
import dev.cruding.engine.action.get.injection.GetByFieldServiceInjection;
import dev.cruding.engine.action.get.injection.GetByFieldViewInjection;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.field.Field;

public class GetByFieldAction extends Action {

    public GetByFieldAction(Entity entity, String lcoreName, Element element) {
        super(ActionType.NOUI, lcoreName, entity, element);
    }

    public GetByFieldAction(Entity entity, Element element) {
        super(ActionType.NOUI, "recuperer", entity, element);
    }

    public void overrideActionInjection() {

        String lcn = "";
        Field[] fieldLists = byField;
        if (fieldLists != null) {
            for (Field c : fieldLists) {
                if (c.isFather || c.isRef) {
                    lcn = lcn + "ParId" + c.uname;
                } else {
                    lcn = lcn + "Par" + c.uname;
                }
            }
        }
        if (lcoreName.equals("recuperer")) {
            lnameWithEntity(lcoreName + entity.uname + lcn);
            lnameWithoutEntity(lcoreName + lcn);
        }

        ctrlActionInjection = new GetByFieldCtrlInjection();
        mdlActionInjection = new GetByFieldMdlInjection();
        resourceActionInjection = new GetByFieldResourceInjection();
        businessActionInjection = new GetBusinessInjection();
        serviceActionInjection = new GetByFieldServiceInjection();
        viewActionInjection = new GetByFieldViewInjection();

    }
}
