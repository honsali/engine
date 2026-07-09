package modules.rh.departement;

import dev.cruding.engine.component.Component;
import modules.rh.RhElementComposer;

public class ViewModifierDepartement extends RhElementComposer {
    public Component rootComponent() {

        return section( //
                element(new FormulaireDepartement(true))//
        );
    }

}
