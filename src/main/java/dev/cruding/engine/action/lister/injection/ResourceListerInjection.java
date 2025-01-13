package dev.cruding.engine.action.lister.injection;

import dev.cruding.engine.flow.JavaFlow;
import dev.cruding.engine.injection.ResourceActionInjection;

public class ResourceListerInjection extends ResourceActionInjection {

    public void addResourceImport(JavaFlow f) {
        f.addJavaImport("java.util.List");
        f.addJavaImport("org.springframework.web.bind.annotation.GetMapping");
        f.addJavaImport("org.springframework.web.bind.annotation.PathVariable");
        f.addJavaImport("java.util.stream.Collectors");
    }

    public void addResourceDeclaration(JavaFlow f) {
        String and = "";
        f.L("");
        f.L____("@GetMapping(\"/", lcoreName());
        if (parIdGrandPere() && entite().haveGrandPere) {
            f.__("/", entite().lgrandPere, "/{id", entite().ugrandPere, "}");
        }
        if (parIdPere() && entite().havePere) {
            f.__("/", entite().lpere, "/{id", entite().upere, "}");
        }
        f.__("\")");
        f.L____("public List<", entite().uname, "Dto> ", lnameSansEntite(), "(");
        if (parIdGrandPere() && entite().haveGrandPere) {
            f.__("@PathVariable Long id", entite().ugrandPere, ", ");
        }
        if (parIdPere() && entite().havePere) {
            f.__("@PathVariable Long id", entite().upere);
        }
        f.__(") {");
        f.L________("return ", entite().lname, "Repository.findAllBy");
        if (parIdGrandPere() && entite().haveGrandPere) {
            f.__(entite().ugrandPere, "_Id");
            and = "And";
        }
        if (parIdPere() && entite().havePere) {
            f.__(and, entite().upere, "_Id");
        }
        f.__("OrderBy", orderBy(), "(");
        if (parIdGrandPere() && entite().haveGrandPere) {
            f.__("id", entite().ugrandPere, ", ");
        }
        if (parIdPere() && entite().havePere) {
            f.__("id", entite().upere);
        }
        f.__(").stream().map(", entite().uname, "Dto::asEntity).collect(Collectors.toList());");
        f.L____("}");
    }
}
