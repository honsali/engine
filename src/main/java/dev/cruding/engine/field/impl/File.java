package dev.cruding.engine.field.impl;

import dev.cruding.engine.element.ElementPrinter;
import dev.cruding.engine.field.Field;

public class File extends Field {

    public File(String lname) {
        super(false);
        lname(lname).jtype("Media").jstype("any").stype("blob");
    }


    public String ui(String element) {
        switch (element) {
            case ElementPrinter.FORM:
                return "ChampFichier";
            case ElementPrinter.DETAIL:
                return "nom";
            case ElementPrinter.TABLEAU:
                return "Colonne";
            default:
                return "";
        }

    }

    protected Field initCopy() {
        return new File(lname);
    }
}
