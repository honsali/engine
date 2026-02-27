package modules.rh.conge;

import dev.cruding.engine.component.Component;
import dev.cruding.engine.gen.ElementComposer;

public class ViewModifierConge extends ElementComposer {
    public ViewModifierConge() {
        super("ViewModifierConge", "/");
    }

    public Component rootComponent() {

        return section( //
                element(new FormulaireConge(true))//
        );
    }

}
