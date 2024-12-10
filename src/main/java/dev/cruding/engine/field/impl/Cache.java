package dev.cruding.engine.field.impl;

import dev.cruding.engine.element.ElementPrinter;
import dev.cruding.engine.field.Field;
import dev.cruding.engine.flow.ViewFlow;

public class Cache extends Field {

    public Cache(Field f) {
        super(f);
        libelle = null;
    }

    public Cache(String lname) {
        super(true);
        lname(lname);
    }

    public void addViewScript(ViewFlow f, String uc, String mvcPath) {}

    public String ui(String element) {
        if (element.equals(ElementPrinter.FORM)) {
            return "ChampCache";
        }
        return super.ui(element);

    }

    protected Field initCopy() {
        return new Custom(lname);
    }
}
