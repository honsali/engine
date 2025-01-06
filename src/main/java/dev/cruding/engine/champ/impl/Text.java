package dev.cruding.engine.champ.impl;

import dev.cruding.engine.champ.Champ;

public class Text extends Champ {

    public Text(String lname) {
        super(true);
        lname(lname).jtype("String").jstype("string").stype("nvarchar(250)");
    }

}
