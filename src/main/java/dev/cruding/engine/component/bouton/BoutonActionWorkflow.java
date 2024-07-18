package dev.cruding.engine.component.bouton;

import org.apache.commons.lang3.StringUtils;
import dev.cruding.engine.element.impl.ElementActionWorkflow;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.flow.Flow;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.Page;

public class BoutonActionWorkflow extends Bouton {

    public BoutonActionWorkflow(Page page, Entity entity, String ltype) {
        super(page, entity);
        ltype(ltype);
        this.element = new ElementActionWorkflow(this);
        page.addElement(element);
        page.addActionInView(this);
    }


    public void addImport(ViewFlow flow) {
        flow.addJsImport("Action" + uname, "./element/Action" + uname);
    }

    public void addOpenTag(ViewFlow flow, int level) {
        flow.addToUi(indent[level]).append("<Action").append(uname).append(" />");
    }

    public void addActionModule(Flow f, Page page) {
        f.L________(StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(lname), "_").toUpperCase(), ": 'Uc", page.uc, ".action." + StringUtils.uncapitalize(ltype), "',");
    }

}
