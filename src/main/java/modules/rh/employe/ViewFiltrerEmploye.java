package modules.rh.employe;

import dev.cruding.engine.composant.Composant;
import dev.cruding.engine.gen.ElementComposer;
import modele.rh.Employe;

public class ViewFiltrerEmploye extends ElementComposer {
    public ViewFiltrerEmploye() {
        super("ViewFiltrerEmploye", "/");
    }

    public Composant composantRacine() {
        Employe e = (Employe) getEntite("Employe");
        FiltreEmploye filtre = new FiltreEmploye();
        Composant elementFiltre = element(filtre);

        return section( //
                enColonne(//
                        panneau(//
                                element(new TableauEmploye(filtre.action)), //
                                blocAction(//
                                        bouton(actionAjouter(e, "PageCreerEmploye"))//
                                )//
                        ).titre("listeEmploye"), //
                        elementFiltre //
                ).largeur(16, 8)//
        );
    }

}
