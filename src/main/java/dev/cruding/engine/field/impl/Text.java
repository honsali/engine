package dev.cruding.engine.field.impl;

import dev.cruding.engine.field.Field;

public class Text extends Field {

    public Text(String lname) {
        super(true);
        lname(lname).jtype("String").jstype("string").stype("nvarchar(250)");
        isText = true;
        maxLength = "250";
    }


    protected Field initCopy() {
        return new Text(lname);
    }
}
