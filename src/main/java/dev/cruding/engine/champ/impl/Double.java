package dev.cruding.engine.champ.impl;

import dev.cruding.engine.champ.Champ;
import dev.cruding.engine.element.ElementPrinter;

public class Double extends Champ {

    public Double(String lname) {
        super(true);
        lname(lname).jtype("Double").jstype("number").stype("double");
    }


    public String ui(String element) {
        switch (element) {
            case ElementPrinter.FORM:
                return "ChampNumerique";
            case ElementPrinter.DETAIL:
                return "Texte";
            case ElementPrinter.TABLEAU:
                return "Colonne tc=\"numerique\"";
            default:
                return "";
        }

    }

    protected Champ initCopy() {
        return new Double(lname);
    }

}
