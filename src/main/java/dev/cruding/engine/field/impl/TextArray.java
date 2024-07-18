package dev.cruding.engine.field.impl;

import dev.cruding.engine.element.Element;
import dev.cruding.engine.field.Field;

public class TextArray extends Field {

    public TextArray(String lname) {
        super(true);
        lname(lname).jtype("String[]").jstype("string[]").stype("varchar");
    }

    public String ui(String element) {
        switch (element) {
        case Element.FORM:
            return "ChampTexte";
        case Element.DETAIL:
            return "nom";
        case Element.TABLEAU:
            return "Colonne tc=\"textArray\"";
        default:
            return "";
        }

    }
}
