package dev.cruding.engine.action.filter.injection;

import java.util.List;
import java.util.stream.Collectors;
import dev.cruding.engine.field.Field;
import dev.cruding.engine.flow.JavaFlow;
import dev.cruding.engine.injection.ActionRepoInjection;

public class RepoFilterInjection extends ActionRepoInjection {

    public void addRepositoryImport(JavaFlow f) {
        f.addJavaImport("org.springframework.data.domain.Page");
        f.addJavaImport("org.springframework.data.domain.Pageable");
        f.addJavaImport("org.springframework.data.jpa.domain.Specification");
        f.addJavaImport("org.springframework.data.jpa.repository.JpaSpecificationExecutor");
        List<Field> refFieldList = entity().fieldList.stream().filter(p -> p.isRef || p.isFather).toList();
        if (refFieldList.size() > 0) {
            f.addJavaImport("org.springframework.data.jpa.repository.EntityGraph");
        }
    }

    public void addRepositoryDeclaration(JavaFlow f) {
        f.L("");
        List<Field> refFieldList = entity().fieldList.stream().filter(p -> p.isRef || p.isFather).toList();
        if (refFieldList.size() > 0) {
            f.L____("@Override");
            f.L____("@EntityGraph(attributePaths = {\"");
            f.__(refFieldList.stream().map(field -> field.lname).collect(Collectors.joining("\", \"")));
            f.__("\"})");
        }
        f.L____("Page<", entity().uname, "> findAll(");
        f.__("Specification<", entity().uname, "> specification, Pageable pageable);");
    }

    public int repositoryDeclarationOrder() {
        return 1;
    }

    public String repositoryExtension() {
        return "JpaSpecificationExecutor<" + entity().uname + ">";
    }
}
