package dev.cruding.engine.action.get.injection;

import java.util.List;
import java.util.stream.Collectors;
import dev.cruding.engine.field.Field;
import dev.cruding.engine.flow.JavaFlow;
import dev.cruding.engine.injection.ActionRepoInjection;

public class GetByFieldRepoInjection extends ActionRepoInjection {

    public void addRepositoryImport(JavaFlow f) {
        if (!entity().listRefAndFather().isEmpty()) {
            f.addJavaImport("java.util.Optional");
            f.addJavaImport("org.springframework.data.jpa.repository.EntityGraph");
        }
    }

    public void addRepositoryDeclaration(JavaFlow f) {
        List<Field> relationFields = entity().listRefAndFather();
        if (!relationFields.isEmpty()) {
            f.L("");
            f.L____("@Override");
            f.L____("@EntityGraph(attributePaths = {\"");
            f.__(relationFields.stream().map(field -> field.lname).collect(Collectors.joining("\", \"")));
            f.__("\"})");
            f.L____("Optional<", entity().uname, "> findById(Long id);");
        }
    }
}
