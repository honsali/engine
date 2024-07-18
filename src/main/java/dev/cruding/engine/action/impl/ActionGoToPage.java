package dev.cruding.engine.action.impl;

import org.apache.commons.lang3.StringUtils;
import dev.cruding.engine.action.ActionInViewOnly;
import dev.cruding.engine.flow.Flow;
import dev.cruding.engine.gen.LabelMapper;
import dev.cruding.engine.gen.Page;

public class ActionGoToPage extends ActionInViewOnly {

    public ActionGoToPage(String lname) {
        super(lname);
        this.lname = lname;
    }

    public void addI18n(Flow f, Page page) {
        String key = "";
        String label = "";
        String uAction = LabelMapper.getInstance().uAction(actionType);
        if (entity == null) {
            key = actionType + page.entityUname;
            label = uAction;
        } else if (!entity.lname.equals(page.entityLname)) {
            String entityLabel = entity.setting.libelle;
            key = actionType + entity.uname;
            label = uAction + " " + entityLabel;
        } else {
            String entityLabel = entity.setting.libelle;
            key = actionType + entity.uname;
            label = uAction + " " + entityLabel;
        }

        f.L____("[Action", page.moduleDefinition.unameLast, ".", "Uc", page.uc, ".", StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(key), "_").toUpperCase(), "]", ": '", label, "',");

    }

    public void addActionModule(Flow f, Page page) {
        if (entity != null) {
            f.L________(StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(actionType + entity.uname), "_").toUpperCase(), ": 'Uc", page.uc, ".action." + StringUtils.uncapitalize(actionType), "',");
        } else {
            f.L________(StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(actionType + page.entityUname), "_").toUpperCase(), ": 'Uc", page.uc, ".action." + StringUtils.uncapitalize(actionType), "',");
        }
    }
}
