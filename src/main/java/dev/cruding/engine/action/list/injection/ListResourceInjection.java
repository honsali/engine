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
        if (byFatherId() && entity().haveFather) {
            f.L____("@GetMapping(\"/", entity().lname, "/", entity().lfather, "/{id", entity().ufather, "}\")");
            f.L____("public List<", entity().uname, "Dto> ", lnameWithoutEntity(), "(@PathVariable Long id", entity().ufather, ") {");
            f.L________("return ", entity().lname, "Service.", lnameWithoutEntity(), "(id", entity().ufather, ");");

            f.L____("}");
        } else {
            f.L____("@GetMapping(\"/", entity().lname, "\")");
            f.L____("public List<", entity().uname, "Dto> ", lnameWithoutEntity(), "() {");
            f.L________("return ", entity().lname, "Service.", lnameWithoutEntity(), "();");
            f.L____("}");
        }
    }
}
