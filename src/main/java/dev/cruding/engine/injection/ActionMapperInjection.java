package dev.cruding.engine.injection;

import java.util.List;
import dev.cruding.engine.action.ActionWrapper;
import dev.cruding.engine.field.Field;
import dev.cruding.engine.flow.JavaFlow;

public class ActionMapperInjection extends ActionWrapper {

    public void addMapperDeclaration(JavaFlow f) {}

    public void addMapperImport(JavaFlow f) {}

    protected String mapperRelationParameters(List<Field> fields) {
        StringBuilder parameters = new StringBuilder();
        for (Field field : fields) {
            parameters.append(", ").append(field.jtype).append(" ").append(field.lname);
        }
        return parameters.toString();
    }
}
