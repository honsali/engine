package dev.cruding.engine.action.impl;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.Context;
import dev.cruding.engine.gen.Page;

public class ActionConsulterElement extends Action {



    public boolean addViewScript(ViewFlow f) {
        f.totalScript().L____("const consulter", entity().uname, " = (", entity().lname, ") => {");
        f.totalScript().L________("goToPage(PageConsulter", entity().uname + ", ", entity().lname, ");");
        f.totalScript().L____("};");
        f.useGoToPage();

        Page targetPage = Context.getInstance().getPage("PageConsulter" + entity().uname);
        if (targetPage == null) {
            System.out.println("PageConsulter" + entity().uname + " not found");
            System.exit(1);
        } else {
            f.addJsImport("{ " + targetPage.name + " }", targetPage.module.listePage(page().path + (inElement() ? "/element/" : "")));
        }
        return true;
    }

}
