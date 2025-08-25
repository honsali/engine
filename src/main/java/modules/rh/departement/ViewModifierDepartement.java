package modules.rh.departement;

import dev.cruding.engine.composant.Composant;
import dev.cruding.engine.gen.ElementComposer;

public class ViewModifierDepartement extends ElementComposer {
    public ViewModifierDepartement() {
        super("ViewModifierDepartement", "/");
    }

    public Composant composantRacine() {

        return section( //
                element(new FormulaireDepartement(true))//
        );
    }

}
