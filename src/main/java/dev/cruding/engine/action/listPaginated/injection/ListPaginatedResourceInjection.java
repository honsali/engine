package dev.cruding.engine.action.listPaginated.injection;

import dev.cruding.engine.flow.JavaFlow;
import dev.cruding.engine.injection.ActionResourceInjection;

public class ListPaginatedResourceInjection extends ActionResourceInjection {

    public void addResourceImport(JavaFlow f) {
        f.addJavaImport("org.springframework.data.domain.Pageable");
        f.addJavaImport("app.core.pagination.PageResponse");
    }

    public void addResourceDeclaration(JavaFlow f) {
        f.L("");
        if (byFatherId() && entity().haveFather) {
            f.L____("@GetMapping(\"/", entity().father.referencedEntity.apiCollectionName(), "/{id", entity().ufather, "}/", entity().apiCollectionName(), "/", lcoreName());
        } else {
            f.L____("@GetMapping(\"/", entity().apiCollectionName(), "/", lcoreName());
        }
        f.__("\")");
        f.L____("public PageResponse<", entity().uname, "Dto> ", lnameWithoutEntity());

        if (byFatherId() && entity().haveFather) {
            f.__("@PathVariable Long id", entity().ufather, ", ");
        }
        f.__("Pageable pageable) {");
        f.L________("return PageResponse.from(", entity().lname, "Repository.findAllBy");

        if (byFatherId() && entity().haveFather) {
            f.__(entity().ufather, "_Id");
        }

        f.__("OrderBy", orderBy(), "(");

        if (byFatherId() && entity().haveFather) {
            f.__("id", entity().ufather, ", ");
        }
        f.__("pageable).map(", entity().uname, "Dto::toDto));");
        f.L____("}");
    }
}
