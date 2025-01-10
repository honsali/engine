package dev.cruding.engine.action.chercher.injection;

import dev.cruding.engine.flow.JavaFlow;
import dev.cruding.engine.injection.RepoActionInjection;

public class RepoChercherInjection extends RepoActionInjection {

    public void addRepositoryImport(JavaFlow f) {
        f.addJavaImport("org.springframework.data.domain.Page");
        f.addJavaImport("org.springframework.data.domain.Pageable");
        f.addJavaImport("org.springframework.data.repository.query.Param");
        f.addJavaImport("org.springframework.data.jpa.repository.Query");
    }

    public void addRepositoryDeclaration(JavaFlow f) {
        f.L("");
        f.L____________("@Query(value = \"select x from ", entite().uname, " x where ( :#{#query.", entite().lid, "} is null OR lower(x.", entite().lid, ") like lower(CONCAT('%',:#{#query.", entite().lid, "},'%')))\")  ");
        f.L____________("Page<", entite().uname, "> chercher(@Param(\"query\") ", entite().uname, " ", entite().lname, ", Pageable pageable);");
    }
}
