package dev.cruding.engine.action.listerEnPage.injection;

import dev.cruding.engine.flow.JavaFlow;
import dev.cruding.engine.injection.RepoActionInjection;

public class RepoListerEnPageInjection extends RepoActionInjection {

    public void addRepositoryImport(JavaFlow f) {
        f.addJavaImport("org.springframework.data.domain.Page");
        f.addJavaImport("org.springframework.data.domain.Pageable");
    }

    public void addRepositoryDeclaration(JavaFlow f) {
        String and = "";
        f.L("");
        f.L____("Page<", entite().uname, "> findAllBy");
        if (parIdGrandPere() && entite().haveGrandPere) {
            f.__(entite().ugrandPere, "_Id");
            and = "And";
        }
        if (parIdPere() && entite().havePere) {
            f.__(and, entite().upere, "_Id");
        }
        f.__("OrderBy", orderBy(), "(");
        if (parIdGrandPere() && entite().haveGrandPere) {
            f.__("Long id", entite().ugrandPere, ", ");
        }
        if (parIdPere() && entite().havePere) {
            f.__("Long id", entite().upere, ", ");
        }
        f.__("Pageable pageable);");
    }
}
