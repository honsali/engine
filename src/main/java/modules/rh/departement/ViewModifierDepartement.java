package modules.rh.departement;

import dev.cruding.engine.component.Component;
import dev.cruding.engine.gen.ViewComposer;
import model.rh.Departement;

public class ViewModifierDepartement extends ViewComposer<Departement> {

    public Component rootComponent() {
        return section( //
                element(new FormulaireDepartement(true))//
        );
    }

}
