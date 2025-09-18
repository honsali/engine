package modules.rh.departement;

import dev.cruding.engine.composant.Composant;
import dev.cruding.engine.gen.ElementComposer;
import modele.rh.Departement;

public class FormulaireDepartement extends ElementComposer {

    private boolean enModification;

    public FormulaireDepartement(boolean enModification) {
        super("FormulaireDepartement", "/element");
        this.enModification = enModification;
    }

    public Composant composantRacine() {
        Departement e = (Departement) getEntite("Departement");
        if (enModification) {
            initModification(e, e.id_).inInit();
        }

        return bloc(//
                formulaire(e, //
                        e.nom, //
                        e.description, //
                        enModification ? cache(e.id_) : null //
                ).nombreColonne(1), //
                blocAction(//
                        enModification ? element(actionMaj(e).siReussi(goToPage(e, "PageConsulterDepartement"))).parForm() : //
                                element(actionCreer(e).siReussi(goToPage(e, "PageConsulterDepartement").parChamp(e.id_))).parForm(), //

                        enModification ? bouton(actionRetourConsulter(e, "PageConsulterDepartement")) : bouton(actionRetourListe(e, "PageListerDepartement"))//
                )//
        ).largeur("600px").marge("20px").fond("blanc");//
    }

}
//
