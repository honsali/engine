package modele.rh;

import dev.cruding.engine.champ.Champ;
import dev.cruding.engine.entite.Entite;


public class Employe extends Entite {

    public final Champ matricule = Texte("matricule").isId();
    public final Champ nom = Texte("nom");
    public final Champ prenom = Texte("prenom");
    public final Champ dateNaissance = Date("dateNaissance");
    public final Champ debutDateNaissance = Date("debutDateNaissance").filtre();
    public final Champ finDateNaissance = Date("finDateNaissance").filtre();
    public final Champ sexe = Ref(Sexe.class);
    public final Champ situationFamiliale = Ref(SituationFamiliale.class);
    public final Champ dateEntree = Date("dateEntree");
    public final Champ debutDateEntree = Date("debutDateEntree").filtre();
    public final Champ finDateEntree = Date("finDateEntree").filtre();
    public final Champ email = Texte("email");
    public final Champ telephone = Texte("telephone");
    public final Champ ville = Texte("ville");
    public final Champ adresse = Texte("adresse");
    public final Champ fonction = Texte("fonction");
    public final Champ description = TexteLong("description");
    public final Champ departement = Ref(Departement.class);

}
