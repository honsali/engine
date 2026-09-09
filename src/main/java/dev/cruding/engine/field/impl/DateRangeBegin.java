package dev.cruding.engine.field.impl;

import dev.cruding.engine.field.Field;

public class DateRangeBegin extends Date {

    public DateRangeBegin(Field f) {
        super();
        lname("debut" + f.uname);
        label = null;
    }


}
