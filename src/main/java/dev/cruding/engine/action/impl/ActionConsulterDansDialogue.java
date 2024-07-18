package dev.cruding.engine.action.impl;

import dev.cruding.engine.action.ActionInViewOnly;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.Page;

public class ActionConsulterDansDialogue extends ActionInViewOnly {


    public ActionConsulterDansDialogue(Page page) {
        super("consulter");
    }

    public ActionConsulterDansDialogue(Page page, Entity entity) {
        super("consulter");
        super.entity(entity);
        this.lname = "consulter" + entity.uname;
    }

    public void addViewScript(ViewFlow f) {

        f.totalScript().L("");
        f.totalScript().L____("const consulter", entity.uname, " = (", entity.lname, ") => {");
        f.totalScript().L____("};");
        f.useGoToPage();


    }

}
