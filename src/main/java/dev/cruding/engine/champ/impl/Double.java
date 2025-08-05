package dev.cruding.engine.champ.impl;

import dev.cruding.engine.champ.Champ;
import dev.cruding.engine.element.Element;

public class Double extends Champ {

    public Double(String lname) {
        super(true);
        lname(lname).jtype("Double").jstype("number").stype("double");
    }

    public String ui(String element) {
        switch (element) {
            case Element.FORM:
                return "ChampDecimal";
            case Element.DETAIL:
                return "Texte";
            case Element.TABLEAU:
                return "Colonne tc=\"decimal\"";
            default:
                return "";
        }
    }

    protected Champ initCopy() {
        return new Double(lname);
    }

}
