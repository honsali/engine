package dev.cruding.engine.field.impl;

import dev.cruding.engine.element.ElementPrinter;
import dev.cruding.engine.field.Field;

public class Int extends Field {

    public Int(String lname) {
        super(true);
        lname(lname).jtype("Integer").jstype("number").stype("int");
    }


    public String ui(String element) {
        switch (element) {
            case ElementPrinter.FORM:
                return "ChampNumerique";
            case ElementPrinter.DETAIL:
                return "nom";
            case ElementPrinter.TABLEAU:
                return "Colonne";
            default:
                return "";
        }

    }


    protected Field initCopy() {
        return new Int(lname);
    }
}
