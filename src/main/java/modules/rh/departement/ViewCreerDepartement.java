package modules.rh.departement;

import dev.cruding.engine.component.Component;
import dev.cruding.engine.gen.ElementComposer;

public class ViewCreerDepartement extends ElementComposer {
    public ViewCreerDepartement() {
        super("ViewCreerDepartement", "/");
    }

    public Component rootComponent() {

        return section( //
                element(new FormulaireDepartement(false)) //
        );
    }

}
