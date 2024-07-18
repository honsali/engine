package dev.cruding.engine.field.impl;

import dev.cruding.engine.element.Element;
import dev.cruding.engine.field.Field;

public class Tel extends Field {

    public Tel(String lname) {
        super(true);
        lname(lname).jtype("String").jstype("string").stype("nvarchar(250)");
    }

    public String ui(String element) {
        switch (element) {
            case Element.FORM:
                return "ChampTel";
            case Element.DETAIL:
                return "nom";
            case Element.TABLEAU:
                return "Colonne";
            default:
                return "";
        }

    }

    protected Field initCopy() {
        return new Tel(lname);
    }
}
