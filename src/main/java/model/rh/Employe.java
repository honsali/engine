package model.rh;

import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.field.Field;


public class Employe extends Entity {

    public final Field matricule = Text("matricule").isId();
    public final Field nom = Text("nom").required();
    public final Field prenom = Text("prenom").required();
    public final Field dateNaissance = Date("dateNaissance").required().filtrable();
    public final Field sexe = Ref(Sexe.class);
    public final Field situationFamiliale = Ref(SituationFamiliale.class);
    public final Field dateEntree = Date("dateEntree").filtrable();
    public final Field email = Text("email");
    public final Field telephone = Text("telephone");
    public final Field ville = Text("ville");
    public final Field adresse = Text("adresse");
    public final Field fonction = Text("fonction");
    public final Field description = LongText("description");
    public final Field departement = Ref(Departement.class);

}
