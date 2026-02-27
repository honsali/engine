package dev.cruding.engine.field.impl;

import dev.cruding.engine.element.Element;
import dev.cruding.engine.field.Field;

public class StaticList extends Field {

    private String type;

    public StaticList(String lname) {
        super(true);
        lname(lname).jtype("String").jstype("string").stype("nvarchar(250)");
    }

    public StaticList(String lname, String type) {
        super(true);
        lname(lname).jtype("String").jstype("string").stype("nvarchar(250)");
        this.type = type;
    }

    public String ui(String element) {
        switch (element) {
            case Element.FORM:
                return "ChampListe" + typeExtension();
            case Element.DETAIL:
                return "Texte";
            case Element.TABLE:
                return "Colonne";
            default:
                return "";
        }

    }

    public String getExtension() {
        String s = " liste={liste" + uname + "}";
        if (type != null && type.endsWith("ertical")) {
            s += " direction=\"vertical\"";
        }
        return s;

    }


    protected Field initCopy() {
        return type != null ? new StaticList(lname, type) : new StaticList(lname);
    }

    private String typeExtension() {
        if (type == null) {
            return "";
        } else if (type.startsWith("radio")) {
            return "Radio";
        } else if (type.startsWith("check")) {
            return "Check";
        } else {
            return "";
        }
    }
}
