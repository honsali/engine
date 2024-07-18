package dev.cruding.engine.field.impl;

import dev.cruding.engine.element.Element;
import dev.cruding.engine.field.Field;

public class Code extends Field {

    public Code(Field f) {
        super(f);
    }

    public String ui(String element) {
        if (element.equals(Element.TABLEAU)) {
            return "Colonne tc=\"code\"";
        }
        return super.ui(element);

    }
}
