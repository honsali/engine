package dev.cruding.engine.action.impl;

import org.apache.commons.lang3.StringUtils;
import dev.cruding.engine.action.Action;
import dev.cruding.engine.flow.Flow;
import dev.cruding.engine.gen.LabelMapper;
import dev.cruding.engine.gen.Page;

public class ActionLocale extends Action {


    public ActionLocale(String actionType) {
        super(actionType);
        this.inViewOnly = true;
    }



    public void addI18n(Flow f, Page page) {
        String entityLabel = entity.setting.libelle;
        String lAction = LabelMapper.getInstance().lAction(lname);
        String uAction = LabelMapper.getInstance().uAction(lname);

        f.L____("[Action", page.moduleDefinition.unameLast, ".", "Uc", page.uc, ".", StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(lname), "_").toUpperCase(), "]", ": '", uAction, " ", entityLabel, "',");

        if (confirmer) {
            f.L____("'Uc", page.uc, ".action.", actionType, ".confirmation.titre': '", uAction, " ", entityLabel, "',");
            f.L____("'Uc", page.uc, ".action.", actionType, ".confirmation.entete': 'Etes vous sur de vouloir ", lAction, " ", entity.setting.ce(), " ", entityLabel, "',");
        }
    }

    public void addActionModule(Flow f, Page page) {
        f.L________(StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(lname), "_").toUpperCase(), ": 'Uc", page.uc, ".action." + StringUtils.uncapitalize(actionType), "',");
    }
}
