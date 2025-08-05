package modules.rh.departement;

import dev.cruding.engine.composant.Composant;
import dev.cruding.engine.gen.ElementComposer;
import modele.rh.Departement;

public class ViewModifierDepartement extends ElementComposer {
    public ViewModifierDepartement() {
        super("ViewModifierDepartement", "/");
    }

    public Composant composantRacine() {

        Departement e = (Departement) getEntite("Departement");
        return section( //
                element(new FormulaireDepartement(true))//
        );
    }

}
