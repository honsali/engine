package modules.rh.departement;

import dev.cruding.engine.composant.Composant;
import dev.cruding.engine.gen.ElementComposer;
import modele.rh.Departement;

public class ViewListerDepartement extends ElementComposer {
    public ViewListerDepartement() {
        super("ViewListerDepartement", "/");
    }

    public Composant composantRacine() {

        Departement e = (Departement) getEntite("Departement");
        return section( //
                element(new TableauDepartement()), //
                blocAction(//
                        bouton(actionAjouter(e, "PageCreerDepartement"))//
                )//
        );
    }

}
