package dev.cruding.engine.champ.impl;

import dev.cruding.engine.champ.Champ;
import dev.cruding.engine.flow.JavaFlow;
import dev.cruding.engine.flow.JsFlow;

public class Child extends Champ {

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
