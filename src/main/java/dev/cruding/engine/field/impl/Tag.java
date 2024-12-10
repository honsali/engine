package dev.cruding.engine.field.impl;

import dev.cruding.engine.element.ElementPrinter;
import dev.cruding.engine.field.Field;

public class Tag extends Field {

    public Tag(Field f) {
        super(f);
    }

    public String ui(String element) {
        if (element.equals(ElementPrinter.TABLEAU)) {
            return "Colonne tc=\"tag\"";
        }
        return super.ui(element);

    }

}
