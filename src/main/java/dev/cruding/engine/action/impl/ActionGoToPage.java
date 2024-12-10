package dev.cruding.engine.action.impl;

import org.apache.commons.lang3.StringUtils;
import dev.cruding.engine.action.Action;
import dev.cruding.engine.component.Component;
import dev.cruding.engine.flow.Flow;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.Context;
import dev.cruding.engine.gen.LabelMapper;
import dev.cruding.engine.gen.Page;

public class ActionGoToPage extends Action {


    private Page targetPage;

    public ActionGoToPage(String targetPage) {
        this.targetPage = Context.getInstance().getPage(targetPage);
    }


    public boolean addViewScript(ViewFlow f) {
        f.addJsImport("{ " + targetPage.name + " }", targetPage.module.listePage(element().path, inElement()));
        f.totalScript().L____("const goToPage", targetPage.name, " = (", entity().lname, ") => {");
        f.totalScript().L________("goToPage(", targetPage.name + ", ", entity().lname, ");");
        f.totalScript().L____("};");
        f.useGoToModule();
        return true;
    }

    public void addFlowScript(ViewFlow f, int level) {
        f.addJsImport("{ " + targetPage.name + " }", targetPage.module.listePage(element().path, inElement()));
        f.totalScript().__(Component.indent[level]).__("goToPage(", targetPage.name, ", resultat);");
        f.useGoToPage();
    }

    public void addI18n(Flow f) {
        String key = "";
        String label = "";
        String uAction = LabelMapper.getInstance().nomAction(lcoreName());
        if (entity() == null) {
            key = lcoreName() + page().entityUname;
            label = uAction;
        } else if (!entity().lname.equals(page().entityLname)) {
            String entityLabel = entity().setting.libelle;
            key = lcoreName() + entity().uname;
            label = uAction + " " + entityLabel;
        } else {
            String entityLabel = entity().setting.libelle;
            key = lcoreName() + entity().uname;
            label = uAction + " " + entityLabel;
        }

        f.L____("[Action", page().module.unameLast, ".", "Uc", uc(), ".", StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(key), "_").toUpperCase(), "]", ": '", label, "',");

    }


}
