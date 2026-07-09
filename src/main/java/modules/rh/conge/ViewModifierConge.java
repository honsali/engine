package modules.rh.conge;

import dev.cruding.engine.component.Component;
import modules.rh.RhElementComposer;

public class ViewModifierConge extends RhElementComposer {
    public Component rootComponent() {

        return section( //
                element(new FormulaireConge(true))//
        );
    }

}
