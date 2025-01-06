package dev.cruding.engine.champ.impl;

import dev.cruding.engine.champ.Champ;
import dev.cruding.engine.element.ElementPrinter;

public class Tag extends Champ {

    public Tag(Champ f) {
        super(f);
    }

    public String ui(String element) {
        if (element.equals(ElementPrinter.TABLEAU)) {
            return "Colonne tc=\"tag\"";
        }
        return super.ui(element);

    }

}