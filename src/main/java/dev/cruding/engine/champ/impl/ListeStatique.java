package dev.cruding.engine.champ.impl;

import dev.cruding.engine.champ.Champ;
import dev.cruding.engine.element.ElementPrinter;

public class ListeStatique extends Champ {

    private String type;

    public ListeStatique(String lname) {
        super(true);
        lname(lname).jtype("String").jstype("string").stype("nvarchar(250)");
    }

    public ListeStatique(String lname, String type) {
        super(true);
        lname(lname).jtype("String").jstype("string").stype("nvarchar(250)");
        this.type = type;
    }

    public String ui(String element) {
        switch (element) {
            case ElementPrinter.FORM:
                return "ChampListe" + typeExtension();
            case ElementPrinter.DETAIL:
                return "Texte";
            case ElementPrinter.TABLEAU:
                return "Colonne";
            default:
                return "";
        }

    }

    public String getExtension() {
        String s = " liste={liste" + uname + "}";
        if ("radioVertical".equals(type)) {
            s += " direction=\"vertical\"";
        }
        return s;

    }


    protected Champ initCopy() {
        return type != null ? new ListeStatique(lname, type) : new ListeStatique(lname);
    }

    private String typeExtension() {
        if (type == null) {
            return "";
        } else if (type.startsWith("radio")) {
            return "Radio";
        } else {
            return "";
        }
    }
}