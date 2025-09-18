package modules.rh.conge;

import dev.cruding.engine.composant.Composant;
import dev.cruding.engine.gen.ElementComposer;

public class ViewModifierConge extends ElementComposer {
    public ViewModifierConge() {
        super("ViewModifierConge", "/");
    }

    public Composant composantRacine() {

        return section( //
                element(new FormulaireConge(true))//
        );
    }

}
