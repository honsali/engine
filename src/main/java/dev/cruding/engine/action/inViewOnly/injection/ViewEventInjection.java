package dev.cruding.engine.action.inViewOnly.injection;

import org.apache.commons.lang3.StringUtils;
import dev.cruding.engine.composant.Composant;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.injection.ViewActionInjection;

public class ViewEventInjection extends ViewActionInjection {

    private String targetEvent;


    public ViewEventInjection(String targetEvent) {
        this.targetEvent = targetEvent;
    };

    public void addFlowScript(ViewFlow f, int level) {
        f.totalScript().__(Composant.indent[level]).__("emit(APP_EVENT.").append(StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(targetEvent), "_").toUpperCase()).append(", resultat);");
        f.useEventBus();
    }

    public boolean addViewScript(ViewFlow f) {
        f.useEventBus();
        f.totalScript().L____("const ").append(lnameSansEntite()).append(" = (");
        if (byRow()) {
            f.totalScript().__(entite().lname);
        }
        f.totalScript().append(") => {");
        f.totalScript().L________("emit(APP_EVENT.").append(StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(targetEvent), "_").toUpperCase());
        if (byEntite()) {
            f.totalScript().__(", ").__(entite().lname);
            f.addSpecificSelector(entite().lname, entite().uname, mvcPath() + "/Mdl" + uc());
        } else if (element().byProp != null) {
            f.totalScript().__(", ").__(element().byProp);
        } else if (byRow()) {
            f.totalScript().__(", ").__(entite().lname);
        }
        f.totalScript().__(");");
        f.totalScript().L____("};");
        return true;
    }
}
