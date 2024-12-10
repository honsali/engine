package dev.cruding.engine.field.impl;

import dev.cruding.engine.element.ElementPrinter;
import dev.cruding.engine.field.Field;
import dev.cruding.engine.flow.JavaFlow;

public class Heure extends Field {

    public Heure(String lname) {
        super(true);
        lname(lname).jtype("LocalTime").jstype("string").stype("date");
    }

    public void addJavaImport(JavaFlow flow) {
        super.addJavaImport(flow);
        flow.addJavaImport("java.time.LocalTime");

    }

    public String ui(String element) {
        switch (element) {
            case ElementPrinter.FORM:
                return "ChampHeure";
            case ElementPrinter.DETAIL:
                return "nom";
            case ElementPrinter.TABLEAU:
                return "Colonne";
            default:
                return "";
        }

    }

    protected Field initCopy() {
        return new Date(lname);
    }
}
