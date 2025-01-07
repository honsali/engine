package dev.cruding.engine.champ.impl;

import dev.cruding.engine.champ.Champ;
import dev.cruding.engine.element.Element;

public class TextArray extends Champ {

    public TextArray(String lname) {
        super(true);
        lname(lname).jtype("String[]").jstype("string[]").stype("varchar");
    }

    public String ui(String element) {
        switch (element) {
            case Element.FORM:
                return "ChampTexte";
            case Element.DETAIL:
                return "Texte";
            case Element.TABLEAU:
                return "Colonne tc=\"textArray\"";
            default:
                return "";
        }

    }
}
