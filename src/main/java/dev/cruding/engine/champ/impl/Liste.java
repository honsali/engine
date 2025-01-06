package dev.cruding.engine.champ.impl;

import dev.cruding.engine.element.ElementPrinter;
import dev.cruding.engine.entite.Entite;

public class Liste<T extends Entite> extends Ref<T> {


    public Liste(Ref<T> r) {
        super(r.type, r.lname);
        copyChampProps(r, this);
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
