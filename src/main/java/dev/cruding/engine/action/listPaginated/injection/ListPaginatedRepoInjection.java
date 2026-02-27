package dev.cruding.engine.action.listPaginated.injection;

import dev.cruding.engine.flow.JavaFlow;
import dev.cruding.engine.injection.ActionRepoInjection;

public class ListPaginatedRepoInjection extends ActionRepoInjection {

    public void addRepositoryImport(JavaFlow f) {
        f.addJavaImport("org.springframework.data.domain.Page");
        f.addJavaImport("org.springframework.data.domain.Pageable");
    }

    public void addRepositoryDeclaration(JavaFlow f) {
        f.L("");
        f.L____("Page<", entity().uname, "> findAllBy");

        if (byFatherId() && entity().haveFather) {
            f.__(entity().ufather, "_Id");
        }
        f.__("OrderBy", orderBy(), "(");

        if (byFatherId() && entity().haveFather) {
            f.__("Long id", entity().ufather, ", ");
        }
        f.__("Pageable pageable);");
    }
}
