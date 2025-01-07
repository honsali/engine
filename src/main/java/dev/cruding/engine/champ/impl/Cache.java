package dev.cruding.engine.champ.impl;

import dev.cruding.engine.champ.Champ;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.flow.ViewFlow;

public class Cache extends Champ {

    public Cache(Champ f) {
        super(f);
        libelle = null;
    }

    public Cache(String lname) {
        super(true);
        lname(lname);
    }

    public void addViewScript(ViewFlow f, String uc, String mvcPath) {}

    public String ui(String element) {
        if (element.equals(Element.FORM)) {
            return "ChampCache";
        }
        return super.ui(element);

    }

    protected Champ initCopy() {
        return new Custom(lname);
    }
}
