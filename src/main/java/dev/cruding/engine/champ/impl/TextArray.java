package dev.cruding.engine.champ.impl;

import dev.cruding.engine.champ.Champ;
import dev.cruding.engine.element.ElementPrinter;

public class TextArray extends Champ {

    public TextArray(String lname) {
        super(true);
        lname(lname).jtype("String[]").jstype("string[]").stype("varchar");
    }

    public String ui(String element) {
        switch (element) {
            case ElementPrinter.FORM:
                return "ChampTexte";
            case ElementPrinter.DETAIL:
                return "Texte";
            case ElementPrinter.TABLEAU:
                return "Colonne tc=\"textArray\"";
            default:
                return "";
        }

    }
}
