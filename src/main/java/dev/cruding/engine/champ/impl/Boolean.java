package dev.cruding.engine.champ.impl;

import dev.cruding.engine.champ.Champ;
import dev.cruding.engine.element.ElementPrinter;

public class Boolean extends Champ {

    public Boolean(String lname) {
        super(true);
        lname(lname).jtype("Boolean").jstype("boolean").stype("boolean");
    }

    public String ui(String element) {
        switch (element) {
            case ElementPrinter.FORM:
                return "ChampOuiNon";
            case ElementPrinter.DETAIL:
                return "OuiNon";
            case ElementPrinter.TABLEAU:
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
