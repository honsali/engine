package modules.rh.departement;

import dev.cruding.engine.composant.Composant;
import dev.cruding.engine.gen.ElementComposer;
import modele.rh.Departement;

public class TableauDepartement extends ElementComposer {


    public TableauDepartement() {
        super("TableauDepartement", "/element");
    }

    public Composant composantRacine() {
        Departement e = (Departement) getEntite("Departement");
        return bloc(//
                tableau(e, //
                        e.nom, //
                        e.description//
                ).remplirPar(listerTout(e)).siCliqueLigne(goToPage(e, "PageConsulterDepartement")) //
        );
    }

}
//
