package modules.rh.departement;

import dev.cruding.engine.composant.Composant;
import dev.cruding.engine.gen.ElementComposer;
import modele.rh.Departement;

public class ViewConsulterDepartement extends ElementComposer {
    public ViewConsulterDepartement() {
        super("ViewConsulterDepartement", "/");
    }

    public Composant composantRacine() {

        Departement e = (Departement) getEntite("Departement");
        return section( //
                element(new EtatDepartement()), //
                blocAction(//
                        bouton(actionModifier(e, "PageModifierDepartement")), //
                        bouton(actionRetourListe(e, "PageListerDepartement")), //
                        bouton(actionSupprimer(e).siReussi(goToPage(e, "PageListerDepartement")))//
                )//
        );
    }

}
