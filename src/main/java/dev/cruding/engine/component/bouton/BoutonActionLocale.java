package dev.cruding.engine.component.bouton;

import org.apache.commons.lang3.StringUtils;
import dev.cruding.engine.action.impl.ActionLocale;
import dev.cruding.engine.element.impl.ElementActionLocale;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.flow.Flow;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.Context;
import dev.cruding.engine.gen.Page;

public class BoutonActionLocale extends Bouton {

    public String idAction;
    public boolean actionCtrl = true;

    public BoutonActionLocale(Page page, Entity entity, String ltype) {
        super(page, entity, ltype);
        this.element = new ElementActionLocale(this);
        this.action = new ActionLocale(ltype);
        this.idAction = Context.getInstance().addAction(action, page, element, entity);
        page.addElement(element);
    }

    public void addImport(ViewFlow flow) {
        flow.addJsImport("Action" + uname, "./element/Action" + uname);
    }

    public void addOpenTag(ViewFlow flow, int level) {
        flow.addToUi(indent[level]).append("<Action").append(uname);
        if (byForm) {
            flow.addToUi(" form={form}");
        }
        flow.addToUi(" />");
    }

    public void addScript(ViewFlow flow) {
        if (byForm) {
            flow.useForm(false);
        }
    }

    public void addCloseTag(ViewFlow c, int level) {}


    public void addActionModule(Flow f, Page page) {
        f.L________(StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(lname), "_").toUpperCase(), ": 'Uc", page.uc, ".action." + StringUtils.uncapitalize(ltype), "',");
    }

}
