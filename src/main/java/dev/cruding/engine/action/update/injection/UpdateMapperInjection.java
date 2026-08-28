package dev.cruding.engine.action.update.injection;

import java.util.List;
import dev.cruding.engine.field.Field;
import dev.cruding.engine.flow.JavaFlow;
import dev.cruding.engine.injection.ActionMapperInjection;

public class UpdateMapperInjection extends ActionMapperInjection {

    @Override
    public void addMapperDeclaration(JavaFlow f) {
        List<Field> fields = entity().listAllFieldButFather();

        f.L("");
        f.L____("public static void toEntity(", entity().uname, " ", entity().lname, ", ", requestName(), " request", mapperRelationParameters(entity().listRef()), ") {");
        f.L________(entity().lname, ".update(");
        for (int i = 0; i < fields.size(); i++) {
            Field field = fields.get(i);
            String value = field.isRef ? field.lname : "request." + field.lname + "()";
            f.L________________(value, i == fields.size() - 1 ? ");" : ",");
        }
        f.L____("}");
    }
}
