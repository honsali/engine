package dev.cruding.engine.field.impl;

import dev.cruding.engine.field.Field;

public class Int extends Field {

    public Int(String lname) {
        super(true);
        lname(lname).jtype("Integer").jstype("number").stype("int");
    }
}
