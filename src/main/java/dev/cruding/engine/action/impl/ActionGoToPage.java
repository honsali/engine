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
        f.totalScript().L____("const goToPage", targetPage.name, " = (", entite().lname, ") => {");
        f.totalScript().L________("goToPage(", targetPage.name + ", ", entite().lname, ");");
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
        if (entite() == null) {
            key = lcoreName() + page().entiteUname;
            label = uAction;
        } else if (!entite().lname.equals(page().entiteLname)) {
            String entiteLabel = entite().setting.libelle;
            key = lcoreName() + entite().uname;
            label = uAction + " " + entiteLabel;
        } else {
            String entiteLabel = entite().setting.libelle;
            key = lcoreName() + entite().uname;
            label = uAction + " " + entiteLabel;
        }

        f.L____("[Action", page().module.unameLast, ".", "Uc", uc(), ".", StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(key), "_").toUpperCase(), "]", ": '", label, "',");

    }


}
