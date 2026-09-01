package dev.cruding.engine.injection;

import java.util.List;
import dev.cruding.engine.action.ActionWrapper;
import dev.cruding.engine.field.Field;
import dev.cruding.engine.flow.JavaFlow;

public class ActionMapperInjection extends ActionWrapper {

    public void addMapperDeclaration(JavaFlow f) {}

    public void addMapperImport(JavaFlow f) {}

    protected List<String> mapperRelationParameters(List<Field> fields) {
        return fields.stream().map(field -> field.jtype + " " + field.lname).toList();
    }
}
