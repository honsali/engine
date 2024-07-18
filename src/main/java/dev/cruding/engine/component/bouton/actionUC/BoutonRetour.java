package dev.cruding.engine.component.bouton.actionUC;

import dev.cruding.engine.component.bouton.BoutonActionUc;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.gen.Page;

public class BoutonRetour extends BoutonActionUc {

    public BoutonRetour(Page page, String targetPage) {
        super(page, targetPage, "Retour");
    }

    public BoutonRetour(Page page, Entity entity, String targetPage) {
        super(page, entity, targetPage, "Retour");
    }
}
