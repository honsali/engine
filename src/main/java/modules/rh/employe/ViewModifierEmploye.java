package modules.rh.employe;

import dev.cruding.engine.component.Component;
import dev.cruding.engine.gen.ElementComposer;

public class ViewModifierEmploye extends ElementComposer {
    public ViewModifierEmploye() {
        super("ViewModifierEmploye", "/");
    }

    public Component rootComponent() {

        return section( //
                element(new FormulaireEmploye(true))//
        );
    }

}
