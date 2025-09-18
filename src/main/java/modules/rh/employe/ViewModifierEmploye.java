package modules.rh.employe;

import dev.cruding.engine.composant.Composant;
import dev.cruding.engine.gen.ElementComposer;

public class ViewModifierEmploye extends ElementComposer {
    public ViewModifierEmploye() {
        super("ViewModifierEmploye", "/");
    }

    public Composant composantRacine() {

        return section( //
                element(new FormulaireEmploye(true))//
        );
    }

}
