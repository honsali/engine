
package dev.cruding.engine.component.bouton;

import dev.cruding.engine.action.impl.ActionSpecifique;
import dev.cruding.engine.element.impl.ElementActionEnregistrer;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.Context;
import dev.cruding.engine.gen.Page;

public class BoutonEnregistrer extends Bouton {

    public BoutonEnregistrer(Page page, Entity entity) {
        this(page, entity, "enregistrer");
    }

    public BoutonEnregistrer(Page page, Entity entity, String ltype) {
        super(page, entity, ltype);
        this.element = new ElementActionEnregistrer(entity, this);
        this.action = new ActionSpecifique(this.ltype);
        this.action.lrest("put").confirmer().byEntity().byForm();
        Context.getInstance().addAction(action, page, element, entity);
        page.addElement(element);
    }

    public void addImport(ViewFlow flow) {
        flow.addJsImport("Action" + uname, "./element/Action" + uname);
    }

    public void addOpenTag(ViewFlow flow, int level) {
        flow.addToUi(indent[level]).append("<Action").append(uname).append(" form={form} />");
    }

    public void addCloseTag(ViewFlow flow, int level) {}

    public void addScript(ViewFlow flow) {
        flow.useForm(false);
    }

}
