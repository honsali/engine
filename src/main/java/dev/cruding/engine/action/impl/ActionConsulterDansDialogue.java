package dev.cruding.engine.action.impl;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.flow.ViewFlow;

public class ActionConsulterDansDialogue extends Action {


    public boolean addViewScript(ViewFlow f) {
        f.totalScript().L____("const consulter", entity().uname, " = (", entity().lname, ") => {");
        f.totalScript().L____("};");
        f.useGoToPage();
        return true;

    }

}
