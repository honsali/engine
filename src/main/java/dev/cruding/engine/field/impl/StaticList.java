package dev.cruding.engine.field.impl;

import dev.cruding.engine.element.Element;
import dev.cruding.engine.field.Field;

public class StaticList extends Text {

    private String type;

    public StaticList(String lname) {
        super(lname);
        isText = false;
    }

    public StaticList(String lname, String type) {
        this(lname);
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
