package modules.rh.conge;

import dev.cruding.engine.composant.Composant;
import dev.cruding.engine.gen.ElementComposer;

public class ViewCreerConge extends ElementComposer {
    public ViewCreerConge() {
        super("ViewCreerEmploye", "/");
    }

    public Composant composantRacine() {

        return section( //
                element(new FormulaireConge(false)) //
        );
    }

}
