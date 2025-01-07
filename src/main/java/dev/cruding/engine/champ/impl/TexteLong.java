package dev.cruding.engine.champ.impl;

import dev.cruding.engine.champ.Champ;
import dev.cruding.engine.element.Element;

public class TexteLong extends Champ {

    public TexteLong(String lname) {
        super(true);
        lname(lname).jtype("String").jstype("string").stype("text");
    }

    public String ui(String element) {
        switch (element) {
            case Element.FORM:
                return "ChampTexteLong";
            case Element.DETAIL:
                return "Texte";
            case Element.TABLEAU:
                return "Colonne";
            default:
                return "";
        }
    }

    protected Champ initCopy() {
        return new TexteLong(lname);
    }
}
