package dev.cruding.engine.action.impl;

import dev.cruding.engine.action.ActionInViewOnly;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.Context;
import dev.cruding.engine.gen.Page;

public class ActionConsulterElement extends ActionInViewOnly {

    private Page page;

    public ActionConsulterElement(Page page) {
        super("consulter");
        this.page = page;
    }

    public ActionConsulterElement(Page page, Entity entity) {
        super("consulter");
        super.entity(entity);
        this.page = page;
        this.lname = "consulter" + entity.uname;
    }

    public void addViewScript(ViewFlow f) {

        f.totalScript().L("");
        f.totalScript().L____("const consulter", entity.uname, " = (", entity.lname, ") => {");
        f.totalScript().L________("goToPage(PageConsulter", entity.uname + ", ", entity.lname, ");");
        f.totalScript().L____("};");
        f.useGoToPage();

        Page targetPage = Context.getInstance().getPage("PageConsulter" + entity.uname);
        if (targetPage == null) {
            System.out.println("PageConsulter" + entity.uname + " not found");
            System.exit(1);
        } else {
            f.addJsImport("{ " + targetPage.name + " }", targetPage.moduleDefinition.listePage(page.path + (inElement ? "/element/" : "")));
        }
    }

}
