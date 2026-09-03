package model.admin;

import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.field.Field;

public class Role extends Entity {

    public final Field code = Text("code").required().isId().maxLength("50");
    public final Field libelle = Text("libelle").required().maxLength("150");
    public final Field description = LongText("description");

    @Override
    public boolean isReferenceData() {
        return true;
    }
}
