package dev.cruding.engine.component.bouton;

import org.apache.commons.lang3.StringUtils;
import dev.cruding.engine.action.impl.ActionSpecifique;
import dev.cruding.engine.element.impl.ElementActionDialogue;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.field.Field;
import dev.cruding.engine.flow.Flow;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.Context;
import dev.cruding.engine.gen.LabelMapper;
import dev.cruding.engine.gen.Page;

public class BoutonActionDialogue extends Bouton {

    public String idAction;
    public boolean actionCtrl = true;

    public BoutonActionDialogue(Page page, Entity entity, String actionType) {
        super(page, entity, actionType);
        this.element = new ElementActionDialogue(this);
        this.actionType = actionType;
        if (actionCtrl) {
            this.action = new ActionSpecifique(actionType);
            this.action.confirmer();
            this.idAction = Context.getInstance().addAction(action, page, element, entity);
        } else {
            page.addActionInView(this);
        }
        page.addElement(element);
    }

    public BoutonActionDialogue(Page page, Entity entity, String actionType, Field... listeChamp) {
        this(page, entity, actionType, true, listeChamp);
    }

    public BoutonActionDialogue(Page page, Entity entity, String actionType, boolean actionCtrl, Field... listeChamp) {
        super(page, entity, actionType);
        this.fieldList = listeChamp;
        this.actionCtrl = actionCtrl;
        this.actionType = actionType;
        this.element = new ElementActionDialogue(this);
        icone = iconeMap.get(actionType);
        if (actionCtrl) {
            this.action = new ActionSpecifique(actionType);
            this.action.confirmer();
            this.idAction = Context.getInstance().addAction(action, page, element, entity);
            if (listeChamp != null) {
                byId().byForm();
            }
        } else {
            page.addActionInView(this);
        }

        page.addElement(element);
    }


    public void addImport(ViewFlow flow) {
        flow.addJsImport("Action" + uname, "./element/Action" + uname);
    }

    public void addOpenTag(ViewFlow flow, int level) {
        flow.addToUi(indent[level]).append("<Action").append(uname).append(" />");
    }

    public void addCloseTag(ViewFlow c, int level) {}


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

            f.L____("'Uc", page.uc, ".action.", key, ".confirmation.titre': '", label, "',");
            f.L____("'Uc", page.uc, ".action.", key, ".confirmation.entete': 'Etes vous sur de vouloir ", lAction, " ", entity.setting.ce(), " ", entityLabel, "',");
        }
    }

    public void addActionModule(Flow f, Page page) {
        if (entity != null) {
            f.L________(StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(actionType + entity.uname), "_").toUpperCase(), ": 'Uc", page.uc, ".action." + StringUtils.uncapitalize(actionType + entity.uname), "',");
        } else {
            f.L________(StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(actionType + page.entityUname), "_").toUpperCase(), ": 'Uc", page.uc, ".action." + StringUtils.uncapitalize(actionType + entity.uname), "',");
        }
    }
}
