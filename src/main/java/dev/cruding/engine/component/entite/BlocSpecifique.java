
package dev.cruding.engine.component.entite;

import org.apache.commons.lang3.StringUtils;
import dev.cruding.engine.component.ElementComponent;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.element.impl.ElementBlocSpecifique;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.Page;

public class BlocSpecifique extends ElementComponent {

    public String lname;
    public String uname;
    public Element element;
    public boolean byForm = false;

    public BlocSpecifique(Page page, String lname) {
        super(page);
        this.lname = lname;
        this.uname = StringUtils.capitalize(lname);
        this.element = new ElementBlocSpecifique(this);
        page.addElement(element);
    }

    public BlocSpecifique byForm() {
        this.byForm = true;
        return this;
    }

    public void addOpenTag(ViewFlow flow, int level) {
        flow.addToUi(indent[level]).append("<Bloc").append(uname);
        if (byForm) {
            flow.addToUi(" form={form}");
        }
        flow.addToUi(" />");
    }

    @Override
    public void addImport(ViewFlow flow) {
        flow.addJsImport("Bloc" + uname, "./element/Bloc" + uname);
    }

    @Override
    public void addCloseTag(ViewFlow c, int level) {}

}
