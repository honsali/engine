package dev.cruding.engine.component.bouton;

import org.apache.commons.lang3.StringUtils;
import dev.cruding.engine.action.Action;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.flow.Flow;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.Context;
import dev.cruding.engine.gen.LabelMapper;
import dev.cruding.engine.gen.Page;

public abstract class BoutonActionUc extends Bouton {


    public BoutonActionUc(Page page, String targetPageName, String actionType) {
        super(page);
        this.targetPage = Context.getInstance().getPage(targetPageName);
        this.actionType = actionType;
        page.addActionInView(this);
    }

    public BoutonActionUc(Page page, Entity entity, String targetPageName, String actionType) {
        super(page, entity);
        this.targetPage = Context.getInstance().getPage(targetPageName);
        this.actionType = actionType;
        page.addActionInView(this);
    }

    public void addImport(ViewFlow flow) {
        flow.addJsImport("{ActionUc" + actionType + "}", "waxant");
        if (targetPage == null) {
            System.out.println("targetPage is null");
        } else {
            flow.addJsImport("{ " + targetPage.name + " }", targetPage.moduleDefinition.listePage(page.path, inElement));
        }
    }


    public void addOpenTag(ViewFlow flow, int level) {

        flow.addToUi(indent[level]).append("<ActionUc").append(actionType);

        if (entity != null && !page.entityLname.equals(entity.lname)) {
            flow.addToUi(" nom=\"").append(StringUtils.uncapitalize(actionType)).append(entity.uname).append("\"");
        }
        if (targetPage != null) {
            flow.addToUi(" page={").append(targetPage.name).append("}");
        }

        flow.addToUi(" />");

    }


    public void addI18n(Flow f, Page page) {
        String key = "";
        String label = "";
        String lAction = LabelMapper.getInstance().lAction(actionType);
        String uAction = LabelMapper.getInstance().uAction(actionType);
        if (entity == null) {
            key = actionType + page.entityUname;
            f.L____("[Action", page.moduleDefinition.unameLast, ".", "Uc", page.uc, ".", StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(key), "_").toUpperCase(), "]", ": '", uAction, "',");

        } else {
            String entityLabel = entity.setting.libelle;
            key = actionType + entity.uname;
            label = uAction + " " + entityLabel;

            f.L____("[Action", page.moduleDefinition.unameLast, ".", "Uc", page.uc, ".", StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(key), "_").toUpperCase(), "]", ": '", uAction, " ", entityLabel, "',");

            if (confirmer) {
                f.L____("'Uc", page.uc, ".action.", actionType, ".confirmation.titre': '", label, "',");
                f.L____("'Uc", page.uc, ".action.", actionType, ".confirmation.entete': 'Etes vous sur de vouloir ", lAction, " ", entity.setting.ce(), " ", entityLabel, "',");
            }
        }
    }

    public void addActionModule(Flow f, Page page) {
        if (entity != null) {
            String key = actionType + entity.uname;
            String lAction = StringUtils.uncapitalize(actionType);
            if (Action.standardActionList.contains(lAction) && entity.lname.equals(page.entityLname)) {
                key = actionType;
            }
            f.L________(StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(actionType + entity.uname), "_").toUpperCase(), ": 'Uc", page.uc, ".action." + StringUtils.uncapitalize(key), "',");
        } else {
            f.L________(StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(actionType + page.entityUname), "_").toUpperCase(), ": 'Uc", page.uc, ".action." + StringUtils.uncapitalize(actionType), "',");
        }
    }

}
