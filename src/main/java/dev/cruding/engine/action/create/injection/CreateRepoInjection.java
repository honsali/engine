package dev.cruding.engine.action.create.injection;

import java.util.List;
import dev.cruding.engine.field.Field;
import dev.cruding.engine.flow.JavaFlow;
import dev.cruding.engine.injection.ActionRepoInjection;

public class CreateRepoInjection extends ActionRepoInjection {

    public void addRepositoryDeclaration(JavaFlow f) {
        List<Field> identifierFields = requestEntityFields().stream().filter(field -> field.isId).toList();
        if (identifierFields.isEmpty()) {
            return;
        }
        f.L("");
        for (Field field : identifierFields) {
            f.L____("boolean existsBy", field.uname, "(String ", field.lname, ");");
        }
    }

    public void addRepositoryImport(JavaFlow f) {}

}
