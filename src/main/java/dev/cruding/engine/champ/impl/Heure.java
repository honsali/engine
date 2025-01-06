package dev.cruding.engine.champ.impl;

import dev.cruding.engine.champ.Champ;
import dev.cruding.engine.element.ElementPrinter;
import dev.cruding.engine.flow.JavaFlow;

public class Heure extends Champ {

    public Heure(String lname) {
        super(true);
        lname(lname).jtype("LocalTime").jstype("string").stype("date");
    }

    public void addJavaImport(JavaFlow flow) {
        super.addJavaImport(flow);
        flow.addJavaImport("java.time.LocalTime");
    }

    public void addDtoImport(JavaFlow flow) {
        flow.addJavaImport("java.time.LocalTime");

    }

    public String ui(String element) {
        switch (element) {
            case ElementPrinter.FORM:
                return "ChampHeure";
            case ElementPrinter.DETAIL:
                return "Texte";
            case ElementPrinter.TABLEAU:
                return "Colonne";
            default:
                return "";
        }
    }

    protected Champ initCopy() {
        return new Date(lname);
    }
}
