package dev.cruding.engine.field.impl;

import dev.cruding.engine.element.ElementPrinter;
import dev.cruding.engine.field.Field;

public class LongText extends Field {

    public LongText(String lname) {
        super(true);
        lname(lname).jtype("String").jstype("string").stype("text");
    }

    public String ui(String element) {

        switch (element) {
        case ElementPrinter.FORM:
            return "ChampTexteLong";
        case ElementPrinter.DETAIL:
            return "nom";
        case ElementPrinter.TABLEAU:
            return "Colonne";
        default:
            return "";
        }

    }

    protected Field initCopy() {
        return new LongText(lname);
    }
}
