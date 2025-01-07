package dev.cruding.engine.action.impl;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.Contexte;
import dev.cruding.engine.gen.Page;

public class ActionConsulterElement extends Action {



    public boolean addViewScript(ViewFlow f) {
        f.totalScript().L____("const consulter", entite().uname, " = (", entite().lname, ") => {");
        f.totalScript().L________("goToPage(PageConsulter", entite().uname + ", ", entite().lname, ");");
        f.totalScript().L____("};");
        f.useGoToPage();

        Page targetPage = Contexte.getInstance().getPage("PageConsulter" + entite().uname);
        if (targetPage == null) {
            System.out.println("PageConsulter" + entite().uname + " not found");
            System.exit(1);
        } else {
            f.addJsImport("{ " + targetPage.name + " }", targetPage.module.listePage(page().path + (inElement() ? "/element/" : "")));
        }
        return true;
    }

}
