package modules.rh.employe;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.composant.Composant;
import dev.cruding.engine.gen.ElementComposer;
import modele.rh.Employe;

public class TableauEmploye extends ElementComposer {


    private Action action;

    public TableauEmploye(Action action) {
        super("TableauEmploye", "/element");
        this.action = action;
    }

    public Composant composantRacine() {
        Employe e = (Employe) getEntite("Employe");
        return bloc(//
                tableau(e, //
                        e.matricule, //
                        e.nom, //
                        e.prenom, //
                        e.fonction, //
                        e.departement//
                ).remplirPar(action).siCliqueLigne(goToPage(e, "PageConsulterEmploye")) //
        );
    }

}
//
