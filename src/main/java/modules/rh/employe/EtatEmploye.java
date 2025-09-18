package modules.rh.employe;

import dev.cruding.engine.composant.Composant;
import dev.cruding.engine.gen.ElementComposer;
import modele.rh.Employe;

public class EtatEmploye extends ElementComposer {


        public EtatEmploye() {
                super("EtatEmploye", "/element");
        }

        public Composant composantRacine() {
                Employe e = (Employe) getEntite("Employe");
                recupererParChamp(e, e.id_).inInit();
                return bloc(//
                                cadreBas(//
                                                etat(e, //
                                                                e.matricule, //
                                                                e.dateEntree, //
                                                                e.departement, //
                                                                e.fonction, //
                                                                e.description.surTouteLaLigne() //
                                                )).titre("employe"), //
                                cadreBas(//
                                                etat(e, //
                                                                e.nom, //
                                                                e.prenom, //
                                                                e.dateNaissance, //
                                                                e.sexe, //
                                                                e.situationFamiliale//
                                                )).titre("personnelle"), //
                                cadreBas(//
                                                etat(e, //
                                                                e.email, //
                                                                e.telephone, //
                                                                e.ville.seulDansLaLigne(), //
                                                                e.adresse.surTouteLaLigne())//
                                ).titre("contact") //
                ).largeur("600px");//
        }

}
//
