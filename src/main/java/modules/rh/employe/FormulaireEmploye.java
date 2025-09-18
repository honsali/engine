package modules.rh.employe;

import dev.cruding.engine.composant.Composant;
import dev.cruding.engine.gen.ElementComposer;
import modele.rh.Employe;

public class FormulaireEmploye extends ElementComposer {

    private boolean enModification;

    public FormulaireEmploye(boolean enModification) {
        super("FormulaireEmploye", "/element");
        this.enModification = enModification;
    }

    public Composant composantRacine() {
        Employe e = (Employe) getEntite("Employe");
        if (enModification) {
            initModification(e, e.id_).inInit();
        }

        return bloc(//
                cadreBas(//
                        formulaire(e, //
                                e.matricule, //
                                e.dateEntree, //
                                e.departement, //
                                e.fonction, //
                                e.description.surTouteLaLigne(), //
                                enModification ? cache(e.id_) : null //
                        )).titre("employe"), //
                cadreBas(//
                        formulaire(e, //
                                e.nom, //
                                e.prenom, //
                                e.dateNaissance, //
                                e.sexe, //
                                e.situationFamiliale//
                        )).titre("personnelle"), //
                cadreBas(//
                        formulaire(e, //
                                e.email, //
                                e.telephone, //
                                e.ville.seulDansLaLigne(), //
                                e.adresse.surTouteLaLigne())//
                ).titre("contact"), //
                blocAction(//
                        enModification ? element(actionMaj(e).siReussi(goToPage(e, "PageConsulterEmploye"))).parForm() : //
                                element(actionCreer(e).siReussi(goToPage(e, "PageConsulterEmploye").parChamp(e.id_))).parForm(), //

                        enModification ? bouton(actionRetourConsulter(e, "PageConsulterEmploye")) : bouton(actionRetourListe(e, "PageFiltrerEmploye"))//
                )//
        ).largeur("600px").marge("20px").fond("blanc");//
    }

}
//
