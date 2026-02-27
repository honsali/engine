package dev.cruding.engine.field.impl;

import dev.cruding.engine.field.Field;

public class DateRangeBegin extends Date {

    public DateRangeBegin(Field f) {
        super("debut" + f.uname);
        label = null;
    }


}
