package dev.cruding.engine.action.lister.injection;

import dev.cruding.engine.flow.JavaFlow;
import dev.cruding.engine.injection.RepoActionInjection;

public class RepoListerInjection extends RepoActionInjection {

    public void addRepositoryImport(JavaFlow f) {
        f.addJavaImport("java.util.List");
    }

    public void addRepositoryDeclaration(JavaFlow f) {
        String and = "";
        f.L("");
        f.L____("List<", entite().uname, "> findAllBy");
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
            f.__("Long id", entite().upere);
        }
        f.__(");");
    }
}
