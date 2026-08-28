package model.admin;

import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.field.Field;

public class Account extends Entity {

    public final Field username = Text("username").maxLength("50").required().isId();
    public final Field passwordHash = Text("passwordHash").maxLength("100").required();
    public final Field role = Ref(Role.class).required();
    public final Field activated = Boolean("activated").required();
    public final Field tokenVersion = Int("tokenVersion").required();
}
