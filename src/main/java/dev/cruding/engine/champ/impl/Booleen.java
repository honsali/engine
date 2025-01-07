package dev.cruding.engine.champ.impl;

import dev.cruding.engine.champ.Champ;
import dev.cruding.engine.element.Element;

public class Booleen extends Champ {

    public Booleen(String lname) {
        super(true);
        lname(lname).jtype("Boolean").jstype("boolean").stype("boolean");
    }

    public String ui(String element) {
        switch (element) {
            case Element.FORM:
                return "ChampOuiNon";
            case Element.DETAIL:
                return "OuiNon";
            case Element.TABLEAU:
                return "Colonne tc=\"ouiNon\"";
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

    protected Booleen initCopy() {
        return new Booleen(lname);
    }
}
