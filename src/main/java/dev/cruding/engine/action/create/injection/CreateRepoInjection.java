package dev.cruding.engine.action.create.injection;

import dev.cruding.engine.field.Field;
import dev.cruding.engine.flow.JavaFlow;
import dev.cruding.engine.injection.ActionRepoInjection;

public class CreateRepoInjection extends ActionRepoInjection {

    public void addRepositoryDeclaration(JavaFlow f) {
        f.L("");
        for (Field field : entity().fieldList) {
            if (field.isId) {
                f.L____("boolean existsBy", field.uname, "(String ", field.lname, ");");
            }
        }
    }

    public void addRepositoryImport(JavaFlow f) {}

}
