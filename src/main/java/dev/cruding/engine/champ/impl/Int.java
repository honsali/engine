package dev.cruding.engine.champ.impl;

import dev.cruding.engine.champ.Champ;
import dev.cruding.engine.element.ElementPrinter;

public class Int extends Champ {

    public Int(String lname) {
        super(true);
        lname(lname).jtype("Integer").jstype("number").stype("int");
    }


    public String ui(String element) {
        switch (element) {
            case ElementPrinter.FORM:
                return "ChampNumerique";
            case ElementPrinter.DETAIL:
                return "Texte";
            case ElementPrinter.TABLEAU:
                return "Colonne";
            default:
                return "";
        }

    }


    protected Champ initCopy() {
        return new Int(lname);
    }
}
