package dev.cruding.engine.action.lister.injection;

import dev.cruding.engine.flow.JavaFlow;
import dev.cruding.engine.injection.RepoActionInjection;

public class RepoListerInjection extends RepoActionInjection {

    public void addRepositoryImport(JavaFlow f) {
        f.addJavaImport("java.util.List");
    }

    public void addRepositoryDeclaration(JavaFlow f) {
        f.L("");
        f.L____("List<", entite().uname, "> findAllBy");

        if (parIdPere() && entite().havePere) {
            f.__(entite().upere, "_Id");
        }
        f.__("OrderBy", orderBy(), "(");

        if (parIdPere() && entite().havePere) {
            f.__("Long id", entite().upere);
        }
        f.__(");");
    }
}
