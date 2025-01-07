package dev.cruding.engine.champ.impl;

import dev.cruding.engine.champ.Champ;

public class Texte extends Champ {

    public Texte(String lname) {
        super(true);
        lname(lname).jtype("String").jstype("string").stype("nvarchar(250)");
    }

}
