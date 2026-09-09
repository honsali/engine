package dev.cruding.engine.field.impl;

import dev.cruding.engine.field.Field;

public class Text extends Field {

    public Text() {
        super(true);
        maxLength = 250;
        jtype("String").jstype("string").stype("nvarchar(" + maxLength + ")");
        isText = true;
    }

    @Override
    public Field maxLength(int maxLength) {
        Field p = super.maxLength(maxLength);
        p.stype("nvarchar(" + maxLength + ")");
        return p;
    }

    protected Field initCopy() {
        return new Text();
    }
}
