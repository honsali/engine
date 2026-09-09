package dev.cruding.engine.field.impl;

import dev.cruding.engine.element.Element;
import dev.cruding.engine.field.Field;

public class Double extends Field {

    public Double() {
        super(true);
        jtype("Double").jstype("number").stype("double");
    }

    public String ui(String element) {
        switch (element) {
            case Element.FORM:
                return "ChampDecimal";
            case Element.DETAIL:
                return "Texte";
            case Element.TABLE:
                return "Colonne tc=\"decimal\"";
            default:
                return "";
        }
    }

    protected Field initCopy() {
        return new Double();
    }

}
