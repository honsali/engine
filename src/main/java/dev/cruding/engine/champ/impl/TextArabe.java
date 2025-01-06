package dev.cruding.engine.champ.impl;

import dev.cruding.engine.champ.Champ;
import dev.cruding.engine.element.ElementPrinter;

public class TextArabe extends Champ {

    public TextArabe(String lname) {
        super(true);
        lname(lname).jtype("String").jstype("string").stype("nvarchar(250)");
    }

    public String ui(String element) {
        switch (element) {
            case ElementPrinter.FORM:
                return "ChampTexteArabe";
            case ElementPrinter.DETAIL:
                return "Texte";
            case ElementPrinter.TABLEAU:
                return "Colonne";
            default:
                return "";
        }

    }
}
