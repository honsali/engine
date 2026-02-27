package modules.rh.employe;

import dev.cruding.engine.component.Component;
import dev.cruding.engine.gen.ElementComposer;

public class ViewCreerEmploye extends ElementComposer {
    public ViewCreerEmploye() {
        super("ViewCreerEmploye", "/");
    }

    public Component rootComponent() {

        return section( //
                element(new FormulaireEmploye(false)) //
        );
    }

}
