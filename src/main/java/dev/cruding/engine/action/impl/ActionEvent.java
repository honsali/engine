package dev.cruding.engine.action.impl;

import org.apache.commons.lang3.StringUtils;
import dev.cruding.engine.action.Action;
import dev.cruding.engine.component.Component;
import dev.cruding.engine.component.bouton.Actionnable;
import dev.cruding.engine.flow.ViewFlow;

public class ActionEvent extends Action {


    private String targetEvent;

    public ActionEvent(String targetEvent) {
        this.targetEvent = targetEvent;
    }

    public void actionnable(Actionnable actionnable) {
        this.actionnable = actionnable;
        this.actionnable.lname(targetEvent);
    }

    public void addFlowScript(ViewFlow f, int level) {
        f.totalScript().__(Component.indent[level]).__("emit(APP_EVENT.").append(StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(targetEvent), "_").toUpperCase()).append(", resultat);");
        f.useEventBus();
    }

    public boolean addViewScript(ViewFlow f) {
        f.useEventBus();
        f.totalScript().L____("const ").append(lcoreName()).append(" = (");
        if (actionnable.byRow) {
            f.totalScript().__(actionnable.entity.lname);
        }
        f.totalScript().append(") => {");
        f.totalScript().L________("emit(APP_EVENT.").append(StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(targetEvent), "_").toUpperCase());
        if (actionnable.byEntity) {
            f.totalScript().__(", ").__(actionnable.entity.lname);
            f.addSpecificSelector(entity().lname, entity().uname, mvcPath() + "/Mdl" + uc());
        } else if (actionnable.element.byProp != null) {
            f.totalScript().__(", ").__(actionnable.element.byProp);
        } else if (actionnable.byRow) {
            f.totalScript().__(", ").__(actionnable.entity.lname);
        }
        f.totalScript().__(");");
        f.totalScript().L____("};");
        return true;
    }

}
