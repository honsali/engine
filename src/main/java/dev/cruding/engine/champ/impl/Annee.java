package dev.cruding.engine.champ.impl;

import dev.cruding.engine.champ.Champ;
import dev.cruding.engine.element.ElementPrinter;
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

    public String ui(String element) {
        switch (element) {
            case ElementPrinter.FORM:
                return "ChampAnnee";
            case ElementPrinter.DETAIL:
                return "Texte";
            case ElementPrinter.TABLEAU:
                return "Colonne";
            default:
                return "";
        }

    }

    protected Champ initCopy() {
        return new Annee(lname);
    }
}