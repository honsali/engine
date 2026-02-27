package dev.cruding.engine.action.inViewOnly;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.action.inViewOnly.injection.GetFromSessionMdlInjection;
import dev.cruding.engine.action.inViewOnly.injection.GetFromSessionViewInjection;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entity.Entity;

public class GetFromSessionAction extends Action {


    public String typeVariable;

    public GetFromSessionAction(Entity entity, Element element, String nameVariable) {
        super(ActionType.FLOW, "###", entity, element);
        isEmpty(true);
        inViewOnly();
        this.nameVariable = nameVariable;
        this.asList = nameVariable != null && nameVariable.startsWith("liste");
    }


    public void overrideActionInjection() {
        initVariable();
        mdlActionInjection = new GetFromSessionMdlInjection(nameVariable, typeVariable);
        viewActionInjection = new GetFromSessionViewInjection(nameVariable);
    }

    private void initVariable() {
        String array = this.asList ? "[]" : "";
        if (entity == null && nameVariable != null) {
            this.typeVariable = "string" + array;
        } else if (entity != null && nameVariable == null) {
            this.nameVariable = this.asList ? "liste" + entity.uname : entity.lname;
            this.typeVariable = "I" + entity.uname + array;
        } else if (entity != null && nameVariable != null) {
            this.typeVariable = "I" + entity.uname + array;
        }
    }

}
