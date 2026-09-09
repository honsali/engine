package dev.cruding.engine.field.impl;

import dev.cruding.engine.element.Element;
import dev.cruding.engine.field.Field;

public class Email extends Text {

    public Email() {
        super();
    }

    public String ui(String element) {
        switch (element) {
            case Element.FORM:
                return "ChampEmail";
            case Element.DETAIL:
                return "Texte";
            case Element.TABLE:
                return "Colonne";
            default:
                return "";
        }
    }

    protected Field initCopy() {
        return new Email();
    }
}
