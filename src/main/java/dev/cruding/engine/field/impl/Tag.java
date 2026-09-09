package dev.cruding.engine.field.impl;

import dev.cruding.engine.element.Element;
import dev.cruding.engine.field.Field;

public class Tag extends Field {

    public Tag(Field f) {
        super(f);
    }

    private Tag() {
        super(true);
    }

    public String ui(String element) {
        if (element.equals(Element.TABLE)) {
            return "Colonne tc=\"tag\"";
        }
        return super.ui(element);
    }

    protected Field initCopy() {
        return new Tag();
    }

}
