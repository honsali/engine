package dev.cruding.engine.entity;

import dev.cruding.engine.field.Field;

public abstract class ReferenceData extends Entity {

    public final Field name = Text().lname("libelle").required().isId();

    public boolean isReferenceData() {
        return true;
    }
}
