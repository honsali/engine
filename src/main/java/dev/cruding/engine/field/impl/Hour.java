package dev.cruding.engine.field.impl;

import dev.cruding.engine.element.Element;
import dev.cruding.engine.field.Field;
import dev.cruding.engine.flow.JavaFlow;

public class Hour extends Field {

    public Hour(String lname) {
        super(true);
        lname(lname).jtype("LocalTime").jstype("string").stype("date");
    }

    public void addJavaImport(JavaFlow f, boolean addGlobal) {
        super.addJavaImport(f, addGlobal);
        f.addJavaImport("java.time.LocalTime");
    }

    public void addDtoImport(JavaFlow flow) {
        flow.addJavaImport("java.time.LocalTime");
    }

    public String ui(String element) {
        switch (element) {
            case Element.FORM:
                return "ChampHeure";
            case Element.DETAIL:
                return "Texte";
            case Element.TABLE:
                return "Colonne";
            default:
                return "";
        }
    }

    protected Field initCopy() {
        return new Date(lname);
    }
}
