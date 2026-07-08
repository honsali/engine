package dev.cruding.engine.action.update.injection;

import dev.cruding.engine.field.Field;
import dev.cruding.engine.flow.JavaFlow;
import dev.cruding.engine.injection.ActionRepoInjection;

public class UpdateRepoInjection extends ActionRepoInjection {

    public void addRepositoryDeclaration(JavaFlow f) {
        f.L("");
        for (Field field : entity().fieldList) {
            if (field.isId) {
                f.L____("boolean existsBy", field.uname, "AndIdNot(String ", field.lname, ", Long id);");
            }
        }
    }

    public void addRepositoryImport(JavaFlow f) {}

}
