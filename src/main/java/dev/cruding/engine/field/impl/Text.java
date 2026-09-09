package dev.cruding.engine.field.impl;

import dev.cruding.engine.field.Field;

public class Text extends Field {

    public Text(String lname) {
        super(true);
        maxLength = "250";
        lname(lname).jtype("String").jstype("string").stype("nvarchar(" + maxLength + ")");
        isText = true;
    }

    @Override
    public Field maxLength(String maxLength) {
        Field p = super.maxLength(maxLength);
        p.stype("nvarchar(" + maxLength + ")");
        return p;
    }

    protected Field initCopy() {
        return new Text(lname);
    }
}
