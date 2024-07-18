package dev.cruding.engine.field.impl;

import dev.cruding.engine.element.Element;
import dev.cruding.engine.field.Field;

public class LongText extends Field {

    public LongText(String lname) {
        super(true);
        lname(lname).jtype("String").jstype("string").stype("text");
    }

    public String ui(String element) {

        switch (element) {
        case Element.FORM:
            return "ChampTexteLong";
        case Element.DETAIL:
            return "nom";
        case Element.TABLEAU:
            return "Colonne";
        default:
            return "";
        }

    }

    protected Field initCopy() {
        return new LongText(lname);
    }
}
