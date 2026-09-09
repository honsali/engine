package model.rh;

import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.field.Field;


public class Conge extends Entity {


    public final Field code = Text().isId();
    public final Field typeConge = Ref(TypeConge.class);
    public final Field dateDebutConge = Date();
    public final Field dateFinConge = Date();
    public final Field commentaire = LongText();
    public final Field employe = Father(Employe.class);

}
