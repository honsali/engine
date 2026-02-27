package modules.rh.conge;

import dev.cruding.engine.component.Component;
import dev.cruding.engine.gen.ElementComposer;

public class ViewCreerConge extends ElementComposer {
    public ViewCreerConge() {
        super("ViewCreerConge", "/");
    }

    public Component rootComponent() {

        return section( //
                element(new FormulaireConge(false)) //
        );
    }

}
