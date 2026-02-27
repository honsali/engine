package dev.cruding.engine.action.inViewOnly.injection;

import org.apache.commons.lang3.StringUtils;
import dev.cruding.engine.component.Component;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.injection.ActionViewInjection;

public class EmitEventViewInjection extends ActionViewInjection {

    private String targetEvent;

    public EmitEventViewInjection(String targetEvent) {
        this.targetEvent = targetEvent;
    };

    public void addFlowScript(ViewFlow f, int level, String args) {
        f.useEventBus();
        f.totalScript().__(Component.indent[level]).__("emit(APP_EVENT.").append(StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(targetEvent), "_").toUpperCase());
        if (args != null) {
            f.totalScript().__(", { ", args, " }");
        }
        f.totalScript().__(");");
    }

    public boolean addViewScript(ViewFlow f) {
        f.useEventBus();
        if (!onSuccessAction()) {
            f.totalScript().L____("const ").append(lnameWithEntity()).append(" = (");
            if (byRow()) {
                f.totalScript().__(entity().lname);
            }
            f.totalScript().append(") => {");
            f.totalScript().L________("emit(APP_EVENT.").append(StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(targetEvent), "_").toUpperCase());
            if (byEntity()) {
                f.totalScript().__(", ").__(entity().lname);
                f.addSelector(entity().lname);
            } else if (element().byProp != null) {
                f.totalScript().__(", ").__(element().byProp);
            } else if (byRow()) {
                f.totalScript().__(", ").__(entity().lname);
            }
            f.totalScript().__(");");
            f.totalScript().L____("};");
        }
        return true;
    }
}
