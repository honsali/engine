package dev.cruding.engine.field.impl;

import dev.cruding.engine.element.ElementPrinter;
import dev.cruding.engine.entity.Entity;

public class Liste<T extends Entity> extends Ref<T> {


    public Liste(Ref<T> r) {
        super(r.type, r.lname);
        copyFieldProps(r, this);
    }

    private Liste(Class<T> type, String lname) {
        super(type, lname);
    }

    public String getExtension() {
        return " liste={liste" + jtype + "}";

    }


    @Override
    public String ui(String element) {
        switch (element) {
            case ElementPrinter.FORM:
                return "ChampListe";
            case ElementPrinter.DETAIL:
                return "reference";
            case ElementPrinter.TABLEAU:
                return "Colonne tc=\"reference\"";
            default:
                return "";
        }

    }

    protected Ref<T> initCopy() {
        return new Liste(type, lname);
    }
}
