package modules.rh.employe;

import dev.cruding.engine.component.Component;
import dev.cruding.engine.gen.ViewComposer;
import model.rh.Employe;

public class ViewModifierEmploye extends ViewComposer<Employe> {

    public Component rootComponent() {

        return section( //
                element(new FormulaireEmploye(true))//
        );
    }

}
