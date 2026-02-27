package dev.cruding.engine.field.impl;

import dev.cruding.engine.field.Field;

public class Id extends Text {


    public Id(Field f) {
        super("id" + f.uname);
    }


}
