package dev.cruding.engine.champ.impl;

import dev.cruding.engine.entite.Entite;
import dev.cruding.engine.flow.Flow;
import dev.cruding.engine.flow.JavaFlow;
import dev.cruding.engine.flow.JsFlow;

public class GrandFather<T extends Entite> extends RefChamp<T> {

    public GrandFather(Class<T> t) {
        super(t, false);
        isFather = true;
    }

    public void addJsDeclaration(JsFlow flow) {}

    public void addJavaDeclaration(JavaFlow flow) {}

    public void addGetterSetter(Flow f) {}
}
