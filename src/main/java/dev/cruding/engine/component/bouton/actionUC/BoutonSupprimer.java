
package dev.cruding.engine.component.bouton.actionUC;

import dev.cruding.engine.component.bouton.BoutonActionUc;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.gen.Page;

public class BoutonSupprimer extends BoutonActionUc {

    public BoutonSupprimer(Page page, String targetPage) {
        super(page, targetPage, "Supprimer");
    }

    public BoutonSupprimer(Page page, Entity entity, String targetPage) {
        super(page, entity, targetPage, "Supprimer");
    }
}
