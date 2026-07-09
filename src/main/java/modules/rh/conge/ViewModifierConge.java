package modules.rh.conge;

import dev.cruding.engine.component.Component;
import dev.cruding.engine.gen.ViewComposer;
import model.rh.Conge;

public class ViewModifierConge extends ViewComposer<Conge> {

    public Component rootComponent() {
        return section( //
                element(new FormulaireConge(true))//
        );
    }

}
