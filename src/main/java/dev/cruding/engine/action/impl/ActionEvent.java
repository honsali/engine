package dev.cruding.engine.action.impl;

import org.apache.commons.lang3.StringUtils;
import dev.cruding.engine.action.ActionPart;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.flow.ViewFlow;

public class ActionEvent extends ActionPart {

    public ActionEvent(String actionType) {
        super(actionType);
    }

    public ActionEvent(Entity entity, String actionType) {
        super(actionType);
        entity(entity);
    }

    public ActionEvent entity(Entity entity) {
        this.entity = entity;
        this.lname = actionType;
        return this;
    }

    public void addViewImport(ViewFlow flow) {
        flow.addJsImport("{ APP_EVENT }", "commun");
        flow.addJsImport("{ eventBus }", "waxant");
    }

    public void addViewScript(ViewFlow f) {
        f.totalScript().L("");
        f.addJsImport("{ APP_EVENT }", "commun");
        f.addJsImport("{ eventBus }", "waxant");
        f.totalScript().L____("const ").append(actionType).append(" = (").append(entity.lname).append(") => {");
        f.totalScript().L________("eventBus.emit(APP_EVENT.").append(StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(actionType), "_").toUpperCase()).append(", ").append(entity.lname).append(");");
        f.totalScript().L____("};");
    }

}
