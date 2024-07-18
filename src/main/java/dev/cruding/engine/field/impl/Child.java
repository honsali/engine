package dev.cruding.engine.field.impl;

import dev.cruding.engine.field.Field;
import dev.cruding.engine.flow.JavaFlow;
import dev.cruding.engine.flow.JsFlow;

public class Child extends Field {

    public Child(String lname) {
        super(false);
        isChild = true;
        lname(lname).jtype(uname);
    }

    public void addJsDeclaration(JsFlow flow) {
    }

    public void addJavaDeclaration(JavaFlow flow) {
    }
}
