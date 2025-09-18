package dev.cruding.engine.action.lister.injection;

import dev.cruding.engine.flow.JavaFlow;
import dev.cruding.engine.injection.ResourceActionInjection;

public class ResourceListerInjection extends ResourceActionInjection {

    public void addResourceImport(JavaFlow f) {
        f.addJavaImport("java.util.List");
        f.addJavaImport("org.springframework.web.bind.annotation.GetMapping");
        f.addJavaImport("org.springframework.web.bind.annotation.PathVariable");
    }

    public void addResourceDeclaration(JavaFlow f) {
        f.L("");
        f.L____("@GetMapping(\"/api/", entite().lname);

        if (parIdPere() && entite().havePere) {
            f.__("/", entite().lpere, "/{id", entite().upere, "}");
        }
        f.__("\")");
        f.L____("public List<", entite().uname, "Dto> ", lnameSansEntite(), "(");

        if (parIdPere() && entite().havePere) {
            f.__("@PathVariable Long id", entite().upere);
        }
        f.__(") {");
        f.L________("return ", entite().lname, "Service.", lnameSansEntite(), "(");

        if (parIdPere() && entite().havePere) {
            f.__("id", entite().upere);
        }
        f.__(");");


        f.L____("}");
    }
}
