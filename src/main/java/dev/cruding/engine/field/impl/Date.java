package dev.cruding.engine.field.impl;

import dev.cruding.engine.element.ElementPrinter;
import dev.cruding.engine.field.Field;
import dev.cruding.engine.flow.JavaFlow;

public class Date extends Field {

    public Date(String lname) {
        super(true);
        lname(lname).jtype("LocalDate").jstype("string").stype("date");
    }

    public void addJavaImport(JavaFlow flow) {
        super.addJavaImport(flow);
        flow.addJavaImport("java.time.LocalDate");

    }

    public String ui(String element) {
        switch (element) {
        case ElementPrinter.FORM:
            return "ChampDate";
        case ElementPrinter.DETAIL:
            return "nom";
        case ElementPrinter.TABLEAU:
            return "Colonne tc=\"date\"";
        default:
            return "";
        }

    }

    protected Field initCopy() {
        return new Date(lname);
    }
}
