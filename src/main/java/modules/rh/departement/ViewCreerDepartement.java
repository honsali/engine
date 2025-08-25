package modules.rh.departement;

import dev.cruding.engine.composant.Composant;
import dev.cruding.engine.gen.ElementComposer;

public class ViewCreerDepartement extends ElementComposer {
    public ViewCreerDepartement() {
        super("ViewCreerDepartement", "/");
    }

    public Composant composantRacine() {

        return section( //
                element(new FormulaireDepartement(false)) //
        );
    }

}
