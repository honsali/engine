package dev.cruding.engine.field.impl;

import dev.cruding.engine.field.Field;

public class DateRangeEnd extends Date {

    public DateRangeEnd(Field f) {
        super("fin" + f.uname);
        label = null;
    }


}
