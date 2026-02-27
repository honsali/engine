package dev.cruding.engine.entity;

import dev.cruding.engine.field.Field;

public abstract class ReferenceData extends Entity {

    public final Field name = Text("libelle").required().isId();
    public final Field code = Text("code").required();

    public boolean isReferenceData() {
        return true;
    }
}
