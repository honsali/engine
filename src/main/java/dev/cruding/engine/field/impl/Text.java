package dev.cruding.engine.field.impl;

import dev.cruding.engine.field.Field;

public class Text extends Field {

    public Text(String lname) {
        super(true);
        lname(lname).jtype("String").jstype("string").stype("nvarchar(250)");
    }

}
