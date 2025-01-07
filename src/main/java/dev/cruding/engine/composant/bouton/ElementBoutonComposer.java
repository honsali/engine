package dev.cruding.engine.composant.bouton;

import dev.cruding.engine.composant.Composant;
import dev.cruding.engine.gen.ElementComposer;

public class ElementBoutonComposer extends ElementComposer {
    private Actionnable actionnable;

    public ElementBoutonComposer(Actionnable actionnable) {
        super("Action" + actionnable.uname, "/element");
        this.actionnable = actionnable;
        this.element.page(actionnable.page);
        this.actionnable.inElement();
        this.actionnable.element(this.element);
    }

    public Composant composantRacine() {
        return new Bouton(actionnable);
    }


}
