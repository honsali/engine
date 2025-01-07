package dev.cruding.engine.champ.impl;

import dev.cruding.engine.entite.Entite;
import dev.cruding.engine.flow.Flow;
import dev.cruding.engine.flow.JavaFlow;
import dev.cruding.engine.flow.JsFlow;

public class GrandPere<T extends Entite> extends ChampRef<T> {

    public GrandPere(Class<T> t) {
        super(t, false);
        isPere = true;
    }

    public void addJsDeclaration(JsFlow flow) {}

    public void addJavaDeclaration(JavaFlow flow) {}

    public void addGetterSetter(Flow f) {}
}
