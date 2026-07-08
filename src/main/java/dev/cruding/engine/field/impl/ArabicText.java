package dev.cruding.engine.field.impl;

import dev.cruding.engine.element.Element;
import dev.cruding.engine.field.Field;

public class ArabicText extends Field {

    public ArabicText(String lname) {
        super(true);
        lname(lname).jtype("String").jstype("string").stype("nvarchar(250)");
        isText = true;
    }

    public String ui(String element) {
        switch (element) {
            case Element.FORM:
                return "ChampTexteArabe";
            case Element.DETAIL:
                return "Texte";
            case Element.TABLE:
                return "Colonne";
            default:
                return "";
        }
    }
}
