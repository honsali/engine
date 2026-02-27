package dev.cruding.engine.action.find.injection;

import dev.cruding.engine.flow.JavaFlow;
import dev.cruding.engine.injection.ActionRepoInjection;

public class FindRepoInjection extends ActionRepoInjection {

    public void addRepositoryImport(JavaFlow f) {
        f.addJavaImport("org.springframework.data.domain.Page");
        f.addJavaImport("org.springframework.data.domain.Pageable");
        f.addJavaImport("org.springframework.data.repository.query.Byam");
        f.addJavaImport("org.springframework.data.jpa.repository.Query");
    }

    public void addRepositoryDeclaration(JavaFlow f) {
        f.L("");
        f.L____________("@Query(value = \"select x from ", entity().uname, " x where ( :#{#query.", entity().lid, "} is null OR lower(x.", entity().lid, ") like lower(CONCAT('%',:#{#query.", entity().lid, "},'%')))\")  ");
        f.L____________("Page<", entity().uname, "> chercher(@Byam(\"query\") ", entity().uname, " ", entity().lname, ", Pageable pageable);");
    }
}
