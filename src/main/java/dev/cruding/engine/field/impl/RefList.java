package dev.cruding.engine.field.impl;

import dev.cruding.engine.element.Element;
import dev.cruding.engine.entity.Entity;

public class RefList<T extends Entity> extends Ref<T> {

    public RefList(Ref<T> r) {
        super(r.type, r.lname);
        copyFieldProps(r, this);
    }

    private RefList(Class<T> type, String lname) {
        super(type, lname);
    }

    public String getExtension() {
        return " liste={liste" + uname + "}";
    }

    public String ui(String element) {
        switch (element) {
            case Element.FORM:
                return "ChampListe";
            case Element.DETAIL:
                return "reference";
            case Element.TABLE:
                return "Colonne tc=\"reference\"";
            default:
                return "";
        }
    }

    protected Ref<T> initCopy() {
        return new RefList<T>(type, lname);
    }
}
