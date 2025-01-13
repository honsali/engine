package dev.cruding.engine.composant.bouton;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.composant.Composant;
import dev.cruding.engine.gen.ElementComposer;

public class ElementBoutonComposer extends ElementComposer {
    private Action action;

    public ElementBoutonComposer(Action action) {
        super("Action" + action.unameAvecEntite, "/element");
        this.action = action;
        this.element.page(action.page);
        this.action.inElement();
        this.action.element(this.element);
    }

    public Composant composantRacine() {
        return new Bouton(action);
    }


}
