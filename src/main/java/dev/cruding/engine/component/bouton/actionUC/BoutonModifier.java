package dev.cruding.engine.component.bouton.actionUC;

import dev.cruding.engine.component.bouton.BoutonActionUc;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.gen.Page;

public class BoutonModifier extends BoutonActionUc {

    public BoutonModifier(Page page, String targetPage) {
        super(page, targetPage, "Modifier");
    }

    public BoutonModifier(Page page, Entity entity, String targetPage) {
        super(page, entity, targetPage, "Modifier");
    }

}
