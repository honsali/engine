package dev.cruding.engine.field.impl;

import dev.cruding.engine.element.Element;
import dev.cruding.engine.field.Field;
import dev.cruding.engine.flow.JavaFlow;

public class Year extends Field {

    public Year(String lname) {
        super(true);
        lname(lname).jtype("LocalDate").jstype("string").stype("date");
    }

    public void addJavaImport(JavaFlow flow) {
        super.addJavaImport(flow);
        flow.addJavaImport("java.time.LocalDate");
    }

    public void addDtoImport(JavaFlow flow) {
        flow.addJavaImport("java.time.LocalDate");
    }

    public String ui(String element) {
        switch (element) {
            case Element.FORM:
                return "ChampAnnee";
            case Element.DETAIL:
                return "Texte";
            case Element.TABLE:
                return "Colonne";
            default:
                return "";
        }
    }

    protected Field initCopy() {
        return new Year(lname);
    }
}
