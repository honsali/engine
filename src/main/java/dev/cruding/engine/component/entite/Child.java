
package dev.cruding.engine.component.entite;

import dev.cruding.engine.component.ElementComponent;
import dev.cruding.engine.element.impl.ChildElement;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.field.Field;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.Page;

public class Child extends ElementComponent {

    public Child(Page page, Entity entity, Field... fieldList) {
        super(page, entity, fieldList);
    }

    public String getTitle() {
        String key = "liste." + fieldList[0].lname;
        addLabel(key, "Liste " + fieldList[0].uname);
        return key;
    }

    public void addImport(ViewFlow flow) {
        flow.addJsImport("Child" + fieldList[0].uname, "./element/Child" + fieldList[0].uname);
    }

    public void addOpenTag(ViewFlow flow, int level) {
        flow.addToUi(indent[level]).append("<Child").append(fieldList[0].uname).append(" />");
    }

    public void addCloseTag(ViewFlow flow, int level) {}

    public void linkToView() {
        page.addElement(new ChildElement(entity, this));
    }

}
