package dev.cruding.engine.action.create.injection;

import java.util.ArrayList;
import java.util.List;

import dev.cruding.engine.field.Field;
import dev.cruding.engine.flow.JavaFlow;
import dev.cruding.engine.injection.ActionMapperInjection;

public class CreateMapperInjection extends ActionMapperInjection {

    @Override
    public void addMapperDeclaration(JavaFlow f) {
        List<Field> fields = entity().fieldList;
        List<Field> relationFields = fields.stream()
                .filter(field -> field.isRef && requestContains(field)
                        || field.isFather && byFatherId() && entity().haveFather)
                .toList();
        List<String> parameters = new ArrayList<>();
        parameters.add(requestName() + " request");
        parameters.addAll(mapperRelationParameters(relationFields));

        f.L("");
        f.addMethodDeclaration(4, "public static " + entity().uname + " toEntity(", parameters);
        f.L________("return new ", entity().uname, "(");
        for (int i = 0; i < fields.size(); i++) {
            Field field = fields.get(i);
            String value;
            if (relationFields.contains(field)) {
                value = field.lname;
            } else if (requestContains(field)) {
                value = "request." + field.lname + "()";
            } else {
                value = "null";
            }
            f.L________________(value, i == fields.size() - 1 ? ");" : ",");
        }
        f.L____("}");
    }
}
