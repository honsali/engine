package dev.cruding.engine.component.bouton.actionUC;

import dev.cruding.engine.component.bouton.BoutonActionUc;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.gen.Page;

public class BoutonAjouter extends BoutonActionUc {

    public BoutonAjouter(Page page, Entity entity) {
        super(page, entity, "PageCreer" + entity.uname, "Ajouter");
    }

    public BoutonAjouter(Page page, String targetPage) {
        super(page, targetPage, "Ajouter");
    }

    public BoutonAjouter(Page page, Entity entity, String targetPage) {
        super(page, entity, targetPage, "Ajouter");
    }

}
