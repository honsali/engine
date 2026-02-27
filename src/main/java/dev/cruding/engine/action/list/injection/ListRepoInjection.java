package dev.cruding.engine.action.list.injection;

import dev.cruding.engine.flow.JavaFlow;
import dev.cruding.engine.injection.ActionRepoInjection;

public class ListRepoInjection extends ActionRepoInjection {

    public void addRepositoryImport(JavaFlow f) {
        f.addJavaImport("java.util.List");
    }

    public void addRepositoryDeclaration(JavaFlow f) {
        f.L("");
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
