package dev.cruding.engine.action.listerEnPage.injection;

import dev.cruding.engine.flow.JavaFlow;
import dev.cruding.engine.injection.ResourceActionInjection;

public class ResourceListerEnPageInjection extends ResourceActionInjection {

    public void addResourceImport(JavaFlow f) {
        f.addJavaImport("org.springdoc.core.annotations.ParameterObject");
        f.addJavaImport("org.springframework.data.domain.Page");
        f.addJavaImport("org.springframework.data.domain.Pageable");
    }

    public void addResourceDeclaration(JavaFlow f) {
        f.L("");
        f.L____("@GetMapping(\"/", lnameSansEntite());
        if (parIdGrandPere() && entite().haveGrandPere) {
            f.__("/", entite().lgrandPere, "/{id", entite().ugrandPere, "}");
        }
        if (parIdPere() && entite().havePere) {
            f.__("/", entite().lpere, "/{id", entite().upere, "}");
        }
        f.__("\")");
        f.L____("public Page<", entite().uname, "Dto> ", lnameSansEntite());
        if (parIdGrandPere() && entite().haveGrandPere) {
            f.__("@PathVariable Long id", entite().ugrandPere, ", ");
        }
        if (parIdPere() && entite().havePere) {
            f.__("@PathVariable Long id", entite().upere, ", ");
        }
        f.__("@ParameterObject Pageable pageable) {");
        f.L________("return ", entite().lname, "Repository.findAll(pageable);");
        f.L____("}");
    }
}
