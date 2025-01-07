package dev.cruding.engine.gen;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entite.Entite;

public class Association {

    protected Entite entite;
    protected Element element;
    protected Action action;

    public Association(Element element, Action action) {
        this.element = element;
        this.action = action;
    }

    public Association(Entite entite, Element element, Action action) {
        this.entite = entite;
        this.element = element;
        this.action = action;
    }

}
