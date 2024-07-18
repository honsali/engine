package dev.cruding.engine.action.impl;

import dev.cruding.engine.action.ActionInViewOnly;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.flow.ViewFlow;

public class ActionVide extends ActionInViewOnly {


    public ActionVide(String actionType) {
        super(actionType);
    }

    public ActionVide(Entity entity, String actionType) {
        super(actionType);
        entity(entity);
    }

    public String actionName() {
        return actionType;
    }

    public void addViewScript(ViewFlow f) {

        f.totalScript().L("");
        if (entity == null) {
            f.totalScript().L____("const ", actionType, " = () => {");
        } else {
            f.totalScript().L____("const ", actionType, " = (", entity.lname, ") => {");
        }
        f.totalScript().L____("};");

    }


}
