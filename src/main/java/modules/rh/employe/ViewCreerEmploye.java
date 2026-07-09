package modules.rh.employe;

import dev.cruding.engine.component.Component;
import modules.rh.RhElementComposer;

public class ViewCreerEmploye extends RhElementComposer {
    public Component rootComponent() {

        return section( //
                element(new FormulaireEmploye(false)) //
        );
    }

}
