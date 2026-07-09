package modules.rh.departement;

import dev.cruding.engine.component.Component;
import modules.rh.RhElementComposer;

public class ViewCreerDepartement extends RhElementComposer {
    public Component rootComponent() {

        return section( //
                element(new FormulaireDepartement(false)) //
        );
    }

}
