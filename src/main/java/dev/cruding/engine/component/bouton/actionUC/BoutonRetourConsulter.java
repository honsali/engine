package dev.cruding.engine.component.bouton.actionUC;

import dev.cruding.engine.component.bouton.BoutonActionUc;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.gen.Page;

public class BoutonRetourConsulter extends BoutonActionUc {

    public BoutonRetourConsulter(Page page, String targetPage) {
        super(page, targetPage, "RetourConsulter");
    }

    public BoutonRetourConsulter(Page page, Entity entity, String targetPage) {
        super(page, entity, targetPage, "RetourConsulter");
    }


}
