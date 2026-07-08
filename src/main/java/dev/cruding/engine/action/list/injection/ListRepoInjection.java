package dev.cruding.engine.action.list.injection;

import java.util.List;
import java.util.stream.Collectors;
import dev.cruding.engine.field.Field;
import dev.cruding.engine.flow.JavaFlow;
import dev.cruding.engine.injection.ActionRepoInjection;

public class ListRepoInjection extends ActionRepoInjection {

    public void addRepositoryImport(JavaFlow f) {
        f.addJavaImport("java.util.List");
        List<Field> refFieldList = entity().fieldList.stream().filter(p -> p.isRef || p.isFather).toList();
        if (refFieldList.size() > 0) {
            f.addJavaImport("org.springframework.data.jpa.repository.EntityGraph");
        }
    }

    public void addRepositoryDeclaration(JavaFlow f) {
        f.L("");
        List<Field> refFieldList = entity().fieldList.stream().filter(p -> p.isRef || p.isFather).toList();
        if (refFieldList.size() > 0) {
            f.L____("@EntityGraph(attributePaths = { \"");
            f.__(refFieldList.stream().map(field -> field.lname).collect(Collectors.joining("\",\"")));
            f.__("\" })");
        }
        f.L____("List<", entity().uname, "> findAllBy");

        if (byFatherId() && entity().haveFather) {
            f.__(entity().ufather, "_Id");
        }
        f.__("OrderBy", orderBy(), "(");

        if (byFatherId() && entity().haveFather) {
            f.__("Long id", entity().ufather);
        }
        f.__(");");
    }
}
