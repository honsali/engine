package dev.cruding.engine.component.bouton.actionUC;

import dev.cruding.engine.component.bouton.BoutonActionUc;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.gen.Page;

public class BoutonRetourListe extends BoutonActionUc {

    public BoutonRetourListe(Page page, String targetPage) {
        super(page, targetPage, "RetourListe");
    }

    public BoutonRetourListe(Page page, Entity entity, String targetPage) {
        super(page, entity, targetPage, "RetourListe");
    }

}
