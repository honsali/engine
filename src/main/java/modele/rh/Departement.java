package modele.rh;

import dev.cruding.engine.champ.Champ;
import dev.cruding.engine.entite.Entite;


public class Departement extends Entite {

    public final Champ nom = Texte("nom").isId();
    public final Champ description = TexteLong("description");

}
