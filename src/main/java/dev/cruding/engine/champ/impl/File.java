package dev.cruding.engine.champ.impl;

import dev.cruding.engine.champ.Champ;
import dev.cruding.engine.element.ElementPrinter;

public class File extends Champ {

    public File(String lname) {
        super(false);
        lname(lname).jtype("Media").jstype("any").stype("blob");
    }


    public String ui(String element) {
        switch (element) {
            case ElementPrinter.FORM:
                return "ChampFichier";
            case ElementPrinter.DETAIL:
                return "Texte";
            case ElementPrinter.TABLEAU:
                return "Colonne";
            default:
                return "";
        }

    }

    protected Champ initCopy() {
        return new File(lname);
    }
}
