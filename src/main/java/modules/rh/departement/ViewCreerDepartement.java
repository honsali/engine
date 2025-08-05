package modules.rh.departement;

import dev.cruding.engine.composant.Composant;
import dev.cruding.engine.gen.ElementComposer;
import modele.rh.Departement;

public class ViewCreerDepartement extends ElementComposer {
    public ViewCreerDepartement() {
        super("ViewCreerDepartement", "/");
    }

    public Composant composantRacine() {

        Departement e = (Departement) getEntite("Departement");
        return section( //
                element(new FormulaireDepartement(false)) //
        );
    }

}
