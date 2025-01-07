package dev.cruding.engine.champ.impl;

import dev.cruding.engine.champ.Champ;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.flow.JavaFlow;

public class Annee extends Champ {

    public Annee(String lname) {
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
            case Element.TABLEAU:
                return "Colonne";
            default:
                return "";
        }
    }

    protected Champ initCopy() {
        return new Annee(lname);
    }
}
