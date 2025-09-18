package dev.cruding.engine.action.listerEnPage.injection;

import dev.cruding.engine.flow.JavaFlow;
import dev.cruding.engine.injection.ResourceActionInjection;

public class ResourceListerEnPageInjection extends ResourceActionInjection {

    public void addResourceImport(JavaFlow f) {
        f.addJavaImport("org.springdoc.api.annotations.ParameterObject");
        f.addJavaImport("org.springframework.data.domain.Page");
        f.addJavaImport("org.springframework.data.domain.Pageable");
    }

    public void addResourceDeclaration(JavaFlow f) {
        f.L("");
        f.L____("@GetMapping(\"/", lcoreName());

        if (parIdPere() && entite().havePere) {
            f.__("/", entite().lpere, "/{id", entite().upere, "}");
        }
        f.__("\")");
        f.L____("public Page<", entite().uname, "Dto> ", lnameSansEntite());

        if (parIdPere() && entite().havePere) {
            f.__("@PathVariable Long id", entite().upere, ", ");
        }
        f.__("@ParameterObject Pageable pageable) {");
        f.L________("return ", entite().lname, "Repository.findAllBy");

        if (parIdPere() && entite().havePere) {
            f.__(entite().upere, "_Id");
        }

        f.__("OrderBy", orderBy(), "(");

        if (parIdPere() && entite().havePere) {
            f.__("id", entite().upere, ", ");
        }
        f.__("pageable).map(", entite().uname, "Dto::toDto);");
        f.L____("}");
    }
}
