package dev.cruding.engine.field.impl;

import dev.cruding.engine.element.ElementPrinter;
import dev.cruding.engine.field.Field;

public class TextArabe extends Field {

    public TextArabe(String lname) {
        super(true);
        lname(lname).jtype("String").jstype("string").stype("nvarchar(250)");
    }

    public String ui(String element) {
        switch (element) {
            case ElementPrinter.FORM:
                return "ChampTexteArabe";
            case ElementPrinter.DETAIL:
                return "nom";
            case ElementPrinter.TABLEAU:
                return "Colonne";
            default:
                return "";
        }

    }
}
