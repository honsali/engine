package dev.cruding.engine.field.impl;

import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.flow.JavaFlow;
import dev.cruding.engine.flow.JsFlow;

public class GrandFather<T extends Entity> extends RefField<T> {

    public GrandFather(Class<T> t) {
        super(t, false);
        isFather = true;
    }

    public void addJsDeclaration(JsFlow flow) {
    }

    public void addJavaDeclaration(JavaFlow flow) {
    }
}
