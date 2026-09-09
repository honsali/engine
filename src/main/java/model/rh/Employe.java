package model.rh;

import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.field.Field;


public class Employe extends Entity {

    public final Field matricule = Text().isId();
    public final Field nom = Text().required();
    public final Field prenom = Text().required();
    public final Field dateNaissance = Date().required().filtrable();
    public final Field sexe = Ref(Sexe.class);
    public final Field situationFamiliale = Ref(SituationFamiliale.class);
    public final Field dateEntree = Date().filtrable();
    public final Field email = Text();
    public final Field telephone = Text();
    public final Field ville = Text();
    public final Field adresse = Text();
    public final Field fonction = Text();
    public final Field description = LongText();
    public final Field departement = Ref(Departement.class);

}
