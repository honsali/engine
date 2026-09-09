package dev.cruding.engine.field.impl;

import dev.cruding.engine.element.Element;
import dev.cruding.engine.field.Field;

public class Int extends Field {

    public Int() {
        super(true);
        jtype("Integer").jstype("number").stype("int");
    }

    public String ui(String element) {
        switch (element) {
            case Element.FORM:
                return "ChampNumerique";
            case Element.DETAIL:
                return "Texte";
            case Element.TABLE:
                return "Colonne tc=\"entier\"";
            default:
                return "";
        }
    }

    protected Field initCopy() {
        return new Int();
    }
}
