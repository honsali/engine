package dev.cruding.engine.field.impl;

import dev.cruding.engine.element.ElementPrinter;
import dev.cruding.engine.field.Field;

public class TextArray extends Field {

    public TextArray(String lname) {
        super(true);
        lname(lname).jtype("String[]").jstype("string[]").stype("varchar");
    }

    public String ui(String element) {
        switch (element) {
        case ElementPrinter.FORM:
            return "ChampTexte";
        case ElementPrinter.DETAIL:
            return "nom";
        case ElementPrinter.TABLEAU:
            return "Colonne tc=\"textArray\"";
        default:
            return "";
        }

    }
}
