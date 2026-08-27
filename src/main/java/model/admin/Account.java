package model.admin;

import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.field.Field;

public class Account extends Entity {

    public final Field username = Text("username").required().isId();
    public final Field password = Text("password");
    public final Field role = Ref(Role.class).required();
    public final Field activated = Boolean("activated").required();
}
