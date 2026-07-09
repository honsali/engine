package dev.cruding.engine.component.button;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.component.Component;
import dev.cruding.engine.gen.BaseComposer;

public class ButtonElementComposer extends BaseComposer {
    private Action action;

    public ButtonElementComposer(Action action) {
        super("Action" + action.unameWithEntity, "/element");
        this.action = action;
        this.element.page(action.page);
        this.action.inElement();
        this.action.element(this.element);
    }

    public Component rootComponent() {
        return new Button(action);
    }


}
