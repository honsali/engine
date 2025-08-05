package dev.cruding.engine.champ.impl;

import dev.cruding.engine.champ.Champ;
import dev.cruding.engine.element.Element;

public class Int extends Champ {

    public Int(String lname) {
        super(true);
        lname(lname).jtype("Integer").jstype("number").stype("int");
    }

    public String ui(String element) {
        switch (element) {
            case Element.FORM:
                return "ChampNumerique";
            case Element.DETAIL:
                return "Texte";
            case Element.TABLEAU:
                return "Colonne tc=\"entier\"";
            default:
                return "";
        }
    }

    protected Champ initCopy() {
        return new Int(lname);
    }
}
