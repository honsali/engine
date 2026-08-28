package model.rh;

import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.field.Field;


public class Conge extends Entity {


    public final Field code = Text("code").isId();
    public final Field typeConge = Ref(TypeConge.class);
    public final Field dateDebutConge = Date("dateDebutConge");
    public final Field dateFinConge = Date("dateFinConge");
    public final Field commentaire = LongText("commentaire");
    public final Field employe = Father(Employe.class);

}
