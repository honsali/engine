package dev.cruding.engine.field.impl;

import dev.cruding.engine.element.Element;
import dev.cruding.engine.field.Field;

public class Hour extends Field {

    public Hour(String lname) {
        super(true);
        lname(lname).jtype("LocalTime").jstype("string").stype("date").isDate(true);
    }

    public String ui(String element) {
        switch (element) {
            case Element.FORM:
                return "ChampHeure";
            case Element.DETAIL:
                return "Texte";
            case Element.TABLE:
                return "Colonne";
            default:
                return "";
        }
    }

    protected Field initCopy() {
        return new Date(lname);
    }
}
