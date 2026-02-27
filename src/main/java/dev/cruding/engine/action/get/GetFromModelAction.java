package dev.cruding.engine.action.get;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.action.get.injection.GetFromModelCtrlInjection;
import dev.cruding.engine.action.get.injection.GetFromModelMdlInjection;
import dev.cruding.engine.action.get.injection.GetFromModelViewInjection;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entity.Entity;

public class GetFromModelAction extends Action {

    private String mdlName = "";
    public String nameVariable;
    public String typeVariable;

    public GetFromModelAction(Entity entity, Element element, String mdlName, String nameVariable) {
        super(ActionType.NOUI, "recupererEnSession", entity, element);
        this.mdlName = mdlName;
        this.nameVariable = nameVariable;
        this.asList = nameVariable != null && nameVariable.startsWith("liste");

    }

    public Action asList() {
        lcoreName("recupererEnSessionListe");
        asList = true;
        return this;
    };


    public void overrideActionInjection() {
        initVariable();
        ctrlActionInjection = new GetFromModelCtrlInjection(mdlName);
        mdlActionInjection = new GetFromModelMdlInjection(nameVariable, typeVariable);
        viewActionInjection = new GetFromModelViewInjection(nameVariable);
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
