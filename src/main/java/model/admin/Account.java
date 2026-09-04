package model.admin;

import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.field.Field;

public class Account extends Entity {

    public final Field username = Text("username").required().isId().maxLength("100").minLength("3");
    public final Field role = Ref(Role.class).required();
    public final Field activated = Boolean("activated").required();
    public final Field password = Text("password").maxLength("256").minLength("8").required();
}
