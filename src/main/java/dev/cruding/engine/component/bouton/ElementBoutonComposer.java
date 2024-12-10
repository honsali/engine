package dev.cruding.engine.component.bouton;

import dev.cruding.engine.component.Component;
import dev.cruding.engine.gen.ElementComposer;

public class ElementBoutonComposer extends ElementComposer {
    Actionnable actionnable;

    public ElementBoutonComposer(Actionnable actionnable) {
        super("Action" + actionnable.uname, "/element");
        this.actionnable = actionnable;
        this.element.page(actionnable.page);
        this.actionnable.inElement();
        this.actionnable.element(this.element);
    }

    public Component composantRacine() {
        return new Bouton(actionnable);
    }


}
