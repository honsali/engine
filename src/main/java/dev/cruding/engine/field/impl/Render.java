package dev.cruding.engine.field.impl;

import dev.cruding.engine.element.Element;
import dev.cruding.engine.field.Field;
import dev.cruding.engine.flow.ViewFlow;

public class Render extends Field {

    public Render(Field f) {
        super(f);
    }

    public Render(String lname) {
        super(true);
        lname(lname);
    }

    public boolean addViewScript(ViewFlow f, String uc, String mvcPath) {
        f.totalScript().L____("const getColonne", uname, " = (texte, element) => {");
        f.totalScript().L________("return <div>{element.", lname, "}</div>;");
        f.totalScript().L____("};");
        f.totalScript().L("");
        return true;
    }

    public String ui(String element) {
        if (element.equals(Element.TABLE)) {
            return "Colonne tc=\"rendu\" content={getColonne" + uname + "}";
        }
        return super.ui(element);

    }

    protected Field initCopy() {
        return new Render(lname);
    }
}
