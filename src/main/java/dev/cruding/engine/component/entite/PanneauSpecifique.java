
package dev.cruding.engine.component.entite;

import org.apache.commons.lang3.StringUtils;
import dev.cruding.engine.component.ElementComponent;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.element.impl.ElementPanneauSpecifique;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.Page;
import dev.cruding.engine.gen.Util;

public class PanneauSpecifique extends ElementComponent {

    public String lname;
    public String uname;
    public Element element;
    public boolean byForm = false;

    public PanneauSpecifique(Page page, String lname) {
        super(page);
        this.lname = lname;
        this.uname = StringUtils.capitalize(lname);
        this.element = new ElementPanneauSpecifique(this);
        page.addElement(element);
    }

    public PanneauSpecifique byForm() {
        this.byForm = true;
        return this;
    }


    @Override
    public void addImport(ViewFlow flow) {
        if (communModule) {
            flow.addJsImport("Panneau" + uname, Util.getRelativePath(page.path, page.moduleDefinition.path, false) + "/commun/Panneau" + uname);
        } else {
            flow.addJsImport("Panneau" + uname, "./element/Panneau" + uname);
        }
    }

    public void addOpenTag(ViewFlow flow, int level) {
        flow.addToUi(indent[level]).append("<Panneau").append(uname);
        if (byForm) {
            flow.addToUi(" form={form}");
        }
        flow.addToUi(" />");
    }

    public void addCloseTag(ViewFlow flow, int level) {}

}
