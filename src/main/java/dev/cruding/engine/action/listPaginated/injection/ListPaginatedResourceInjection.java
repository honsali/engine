package dev.cruding.engine.action.listPaginated.injection;

import dev.cruding.engine.flow.JavaFlow;
import dev.cruding.engine.injection.ActionResourceInjection;

public class ListPaginatedResourceInjection extends ActionResourceInjection {

    public void addResourceImport(JavaFlow f) {
        f.addJavaImport("org.springframework.data.domain.Pageable");
        f.addJavaImport("app.core.PageResponse");
    }

    public void addResourceDeclaration(JavaFlow f) {
        f.L("");
        f.L____("@GetMapping(\"/", lcoreName());

        if (byFatherId() && entity().haveFather) {
            f.__("/", entity().lfather, "/{id", entity().ufather, "}");
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
