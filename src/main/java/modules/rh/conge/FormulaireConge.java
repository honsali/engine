package modules.rh.conge;

import dev.cruding.engine.composant.Composant;
import dev.cruding.engine.gen.ElementComposer;
import modele.rh.Conge;

public class FormulaireConge extends ElementComposer {

    private boolean enModification;

    public FormulaireConge(boolean enModification) {
        super("FormulaireConge", "/element");
        this.enModification = enModification;
    }

    public Composant composantRacine() {
        Conge e = (Conge) getEntite("Conge");
        if (enModification) {
            initModification(e, e.id_).inInit();
        }

        return bloc(//
                formulaire(e, //
                        e.typeConge, //
                        e.dateDebutConge, //
                        e.dateFinConge, //
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
