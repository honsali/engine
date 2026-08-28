package dev.cruding.engine.field.impl;

import dev.cruding.engine.element.Element;
import dev.cruding.engine.field.Field;

public class Year extends Field {

    public Year(String lname) {
        super(true);
        lname(lname).jtype("LocalDate").jstype("string").stype("date").isDate(true);
    }


    public String ui(String element) {
        switch (element) {
            case Element.FORM:
                return "ChampAnnee";
            case Element.DETAIL:
                return "Texte";
            case Element.TABLE:
                return "Colonne";
            default:
                return "";
        }
    }

    protected Field initCopy() {
        return new Year(lname);
    }
}
