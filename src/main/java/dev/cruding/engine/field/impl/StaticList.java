package dev.cruding.engine.field.impl;

import dev.cruding.engine.element.Element;
import dev.cruding.engine.field.Field;

public class StaticList extends Text {

    private String type;

    public StaticList() {
        super();
        isText = false;
    }

    public StaticList type(String type) {
        StaticList p = (StaticList) makeCopy();
        p.type = type;
        return p;
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
        StaticList p = new StaticList();
        p.type = type;
        return p;
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
