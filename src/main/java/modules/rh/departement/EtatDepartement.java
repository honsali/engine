package modules.rh.departement;

import dev.cruding.engine.composant.Composant;
import dev.cruding.engine.gen.ElementComposer;
import modele.rh.Departement;

public class EtatDepartement extends ElementComposer {


    public EtatDepartement() {
        super("EtatDepartement", "/element");
    }

    public Composant composantRacine() {
        Departement e = (Departement) getEntite("Departement");
        recupererParChamp(e, e.id_).inInit();
        return bloc(//
                etat(e, //
                        e.nom, //
                        e.description//
                )//
        ).largeur("600px").marge("20px");//
    }

}
//
