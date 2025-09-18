package modules.rh.employe;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.composant.Composant;
import dev.cruding.engine.gen.ElementComposer;
import modele.rh.Employe;

public class FiltreEmploye extends ElementComposer {

        public Action action;

        public FiltreEmploye() {
                super("FiltreEmploye", "/element");
        }

        public Composant composantRacine() {
                Employe e = (Employe) getEntite("Employe");
                action = filtrer(e).filtrerAuDepart();

                return panneauFiltre(e, true, //
                                panneauEtendable(//
                                                formulaire(e, //
                                                                e.matricule.seulDansLaLigne(), //
                                                                e.debutDateEntree, //
                                                                e.finDateEntree, //
                                                                e.departement, //
                                                                e.fonction //
                                                )).open().titre("employe"), //
                                panneauEtendable(//
                                                formulaire(e, //
                                                                e.nom, //
                                                                e.prenom, //
                                                                e.debutDateNaissance, //
                                                                e.finDateNaissance, //
                                                                e.sexe, //
                                                                e.situationFamiliale//
                                                )).titre("personnelle"), //
                                panneauEtendable(//
                                                formulaire(e, //
                                                                e.email, //
                                                                e.telephone, //
                                                                e.ville, //
                                                                e.adresse)//
                                ).titre("contact"), //
                                separateur(), //
                                blocAction(//

                                                bouton(appliquerFiltre(e, action)), //
                                                bouton(initialiserFiltre(e, action))//
                                )//
                ).titre("filtreEmploye");
        }

}
