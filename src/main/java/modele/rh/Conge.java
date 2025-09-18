package modele.rh;

import dev.cruding.engine.champ.Champ;
import dev.cruding.engine.entite.Entite;


public class Conge extends Entite {


    public final Champ code = Texte("code").isId();
    public final Champ typeConge = Ref(TypeConge.class);
    public final Champ dateDebutConge = Date("dateDebutConge");
    public final Champ dateFinConge = Date("dateFinConge");
    public final Champ commentaire = TexteLong("commentaire");
    public final Champ employe = Pere(Employe.class);

}
