package dev.cruding.engine.field.impl;

import dev.cruding.engine.element.Element;
import dev.cruding.engine.field.Field;

public class Hidden extends Field {

    public Hidden(Field f) {
        super(f);
        label = null;
    }

    public Hidden(String lname) {
        super(true);
        lname(lname);
    }


    public String ui(String element) {
        if (element.equals(Element.FORM)) {
            return "ChampCache";
        }
        return super.ui(element);

    }

    protected Field initCopy() {
        return new Custom(lname);
    }
}
