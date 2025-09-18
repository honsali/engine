package modules.rh.employe;

import dev.cruding.engine.composant.Composant;
import dev.cruding.engine.gen.ElementComposer;

public class ViewCreerEmploye extends ElementComposer {
    public ViewCreerEmploye() {
        super("ViewCreerEmploye", "/");
    }

    public Composant composantRacine() {

        return section( //
                element(new FormulaireEmploye(false)) //
        );
    }

}
