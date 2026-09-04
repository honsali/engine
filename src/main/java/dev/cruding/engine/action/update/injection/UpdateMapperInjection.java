package dev.cruding.engine.action.update.injection;

import java.util.ArrayList;
import java.util.List;
import dev.cruding.engine.field.Field;
import dev.cruding.engine.flow.JavaFlow;
import dev.cruding.engine.injection.ActionMapperInjection;

public class UpdateMapperInjection extends ActionMapperInjection {

    @Override
    public void addMapperDeclaration(JavaFlow f) {
        List<Field> fields = entity().listAllFieldButFather();
        List<Field> relationFields = entity().listRef().stream().filter(this::requestContains).toList();
        List<String> parameters = new ArrayList<>();
        parameters.add(entity().uname + " " + entity().lname);
        parameters.add(requestName() + " request");
        parameters.addAll(mapperRelationParameters(relationFields));

        f.L("");
        f.addMethodDeclaration(4, "public static void toEntity(", parameters);
        f.L________(entity().lname, ".update(");
        for (int i = 0; i < fields.size(); i++) {
            Field field = fields.get(i);
            String value;
            if (relationFields.contains(field)) {
                value = field.lname;
            } else if (requestContains(field)) {
                value = "request." + field.lname + "()";
            } else {
                value = entity().lname + ".get" + field.uname + "()";
            }
            f.L________________(value, i == fields.size() - 1 ? ");" : ",");
        }
        f.L____("}");
    }
}
