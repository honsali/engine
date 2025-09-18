package modules.rh.conge;

import dev.cruding.engine.composant.Composant;
import dev.cruding.engine.gen.ElementComposer;
import modele.rh.Conge;

public class TableauConge extends ElementComposer {



    public TableauConge() {
        super("TableauConge", "/element");
    }

    public Composant composantRacine() {
        Conge e = (Conge) getEntite("Conge");
        return bloc(//
                tableau(e, //
                        e.typeConge, //
                        e.dateDebutConge, //
                        e.dateFinConge, //
                        e.commentaire//
                ).remplirPar(listerTout(e).parIdPere()) //
        );
    }

}
//
