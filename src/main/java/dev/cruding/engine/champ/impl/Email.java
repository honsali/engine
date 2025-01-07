package dev.cruding.engine.champ.impl;

import dev.cruding.engine.champ.Champ;
import dev.cruding.engine.element.Element;

public class Email extends Champ {

    public Email(String lname) {
        super(true);
        lname(lname).jtype("String").jstype("string").stype("nvarchar(250)");
    }

    public String ui(String element) {
        switch (element) {
            case Element.FORM:
                return "ChampEmail";
            case Element.DETAIL:
                return "Texte";
            case Element.TABLEAU:
                return "Colonne";
            default:
                return "";
        }
    }

    protected Champ initCopy() {
        return new Email(lname);
    }
}
