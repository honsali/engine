package dev.cruding.engine.field.impl;

import dev.cruding.engine.element.Element;
import dev.cruding.engine.field.Field;

public class Boolean extends Field {

    public Boolean(String lname) {
        super(true);
        lname(lname).jtype("Boolean").jstype("boolean").stype("boolean");
    }

    public String ui(String element) {
        switch (element) {
            case Element.FORM:
                return "ChampOuiNon";
            case Element.DETAIL:
                return "OuiNon";
            case Element.TABLE:
                return "Colonne tc=\"ouiNon\"";
            default:
                return "";
        }
    }

    public String getExtension() {
        String s = "";
        if (yesValue != null) {
            s = s + " oui=\"" + yesValue + "\"";
        }
        if (noValue != null) {
            s = s + " non=\"" + noValue + "\"";
        }
        return s;
    }

    protected Boolean initCopy() {
        return new Boolean(lname);
    }
}
