package dev.cruding.engine.action.list.injection;

import dev.cruding.engine.flow.JavaFlow;
import dev.cruding.engine.injection.ActionResourceInjection;

public class ListResourceInjection extends ActionResourceInjection {

    public void addResourceImport(JavaFlow f) {
        f.addJavaImport("java.util.List");
        f.addJavaImport("org.springframework.web.bind.annotation.GetMapping");
        f.addJavaImport("org.springframework.web.bind.annotation.PathVariable");
    }

    public void addResourceDeclaration(JavaFlow f) {
        f.L("");
        f.L____("@GetMapping(\"/api/", entity().lname);

        if (byFatherId() && entity().haveFather) {
            f.__("/", entity().lfather, "/{id", entity().ufather, "}");
        }
        f.__("\")");
        f.L____("public List<", entity().uname, "Dto> ", lnameWithoutEntity(), "(");

        if (byFatherId() && entity().haveFather) {
            f.__("@PathVariable Long id", entity().ufather);
        }
        f.__(") {");
        f.L________("return ", entity().lname, "Service.", lnameWithoutEntity(), "(");

        if (byFatherId() && entity().haveFather) {
            f.__("id", entity().ufather);
        }
        f.__(");");


        f.L____("}");
    }
}
