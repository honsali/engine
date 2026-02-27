package dev.cruding.engine.field.impl;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.component.button.Button;
import dev.cruding.engine.element.ComponentWrappingElement;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.field.Field;
import dev.cruding.engine.flow.ViewFlow;

public class ActionColumn extends Field {

    private Button component;
    private Action action;
    private ComponentWrappingElement componentWrappingElement;

    public ActionColumn() {
        super(true);
    }

    public ActionColumn(Entity entity, Button b) {
        super(true);
        lname(b.action.lcoreName);
        this.component = b;
        this.component.action.byProp(entity.lname + ":element");
    }

    public ActionColumn(Entity entity, String lname, ComponentWrappingElement c) {
        super(true);
        lname(lname);
        this.componentWrappingElement = c;
        this.componentWrappingElement.byProp(entity.lname + ":element");
    }

    public ActionColumn(Entity entity, Action action) {
        super(true);
        lname(action.lnameWithEntity);
        this.action = action;
    }

    public boolean addViewScript(ViewFlow f, String uc, String mvcPath) {

        f.totalScript().L____("const getColonne", uname, " = (texte, element) => {");
        f.totalScript().L________("return ");
        if (action == null) {
            if (component != null) {
                component.addImport(f);
                component.appendContent(f, f.totalScript());
            } else if (componentWrappingElement != null) {
                componentWrappingElement.appendContent(f, f.totalScript());
            }
        } else {
            action.viewActionInjection.addViewScript(f);
        }
        f.totalScript().__(";");
        f.totalScript().L____("};");
        return true;
    }

    public String ui(String element) {
        if (element.equals(Element.TABLE)) {
            return "Colonne tc=\"custom\" content={getColonne" + uname + "}";
        }
        return super.ui(element);

    }


    protected ActionColumn initCopy() {
        return new ActionColumn();
    }

    protected ActionColumn makeCopy() {
        ActionColumn p = initCopy();
        p.component = this.component;
        p.action = this.action;
        p.componentWrappingElement = this.componentWrappingElement;
        return copyFieldProps(this, p);
    }


}
