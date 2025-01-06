package dev.cruding.engine.champ.impl;

import dev.cruding.engine.champ.Champ;
import dev.cruding.engine.element.ElementPrinter;

public class LongText extends Champ {

    public LongText(String lname) {
        super(true);
        lname(lname).jtype("String").jstype("string").stype("text");
    }

    public String ui(String element) {

        switch (element) {
            case ElementPrinter.FORM:
                return "ChampTexteLong";
            case ElementPrinter.DETAIL:
                return "Texte";
            case ElementPrinter.TABLEAU:
                return "Colonne";
            default:
                return "";
        }

    }

    protected Champ initCopy() {
        return new LongText(lname);
    }
}
