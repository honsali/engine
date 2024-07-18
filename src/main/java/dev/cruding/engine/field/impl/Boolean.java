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
            return "ouiNon";
        case Element.TABLEAU:
            return "Colonne tc=\"boolean\"";
        default:
            return "";
        }

    }

    public String getExtension() {
        String s = "";
        if (oui != null) {
            s = s + " oui=\"" + oui + "\"";
        }
        if (non != null) {
            s = s + " non=\"" + non + "\"";
        }
        return s;
    }

    protected Boolean initCopy() {
        return new Boolean(lname);
    }
}
