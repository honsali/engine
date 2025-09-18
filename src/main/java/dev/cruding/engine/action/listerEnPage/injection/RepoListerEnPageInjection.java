package dev.cruding.engine.action.listerEnPage.injection;

import dev.cruding.engine.flow.JavaFlow;
import dev.cruding.engine.injection.RepoActionInjection;

public class RepoListerEnPageInjection extends RepoActionInjection {

    public void addRepositoryImport(JavaFlow f) {
        f.addJavaImport("org.springframework.data.domain.Page");
        f.addJavaImport("org.springframework.data.domain.Pageable");
    }

    public void addRepositoryDeclaration(JavaFlow f) {
        f.L("");
        f.L____("Page<", entite().uname, "> findAllBy");

        if (parIdPere() && entite().havePere) {
            f.__(entite().upere, "_Id");
        }
        f.__("OrderBy", orderBy(), "(");

        if (parIdPere() && entite().havePere) {
            f.__("Long id", entite().upere, ", ");
        }
        f.__("Pageable pageable);");
    }
}
