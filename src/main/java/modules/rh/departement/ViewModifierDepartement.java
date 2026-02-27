package modules.rh.departement;

import dev.cruding.engine.component.Component;
import dev.cruding.engine.gen.ElementComposer;

public class ViewModifierDepartement extends ElementComposer {
    public ViewModifierDepartement() {
        super("ViewModifierDepartement", "/");
    }

    public Component rootComponent() {

        return section( //
                element(new FormulaireDepartement(true))//
        );
    }

}
