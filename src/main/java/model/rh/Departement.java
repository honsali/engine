package model.rh;

import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.field.Field;


public class Departement extends Entity {

    public final Field nom = Text("nom").required().isId();
    public final Field description = LongText("description");

}
