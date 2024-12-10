package dev.cruding.engine.field.impl;

import dev.cruding.engine.element.ElementPrinter;
import dev.cruding.engine.field.Field;

public class Double extends Field {

    public Double(String lname) {
        super(true);
        lname(lname).jtype("Double").jstype("number").stype("double");
    }


    public String ui(String element) {
        switch (element) {
            case ElementPrinter.FORM:
                return "ChampNumerique";
            case ElementPrinter.DETAIL:
                return "nom";
            case ElementPrinter.TABLEAU:
                return "Colonne tc=\"numerique\"";
            default:
                return "";
        }

    }

    protected Field initCopy() {
        return new Double(lname);
    }

}
