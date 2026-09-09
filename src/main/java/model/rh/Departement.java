package model.rh;

import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.field.Field;


public class Departement extends Entity {

    public final Field nom = Text().required().isId().maxLength(150);
    public final Field description = LongText().maxLength(1000);

}
