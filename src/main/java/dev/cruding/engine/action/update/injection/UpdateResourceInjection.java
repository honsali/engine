package dev.cruding.engine.action.update.injection;

import dev.cruding.engine.action.specifique.injection.BasicResourceInjection;
import dev.cruding.engine.flow.JavaFlow;

public class UpdateResourceInjection extends BasicResourceInjection {

    public void addResourceImport(JavaFlow f) {
        f.addJavaImport("jakarta.validation.Valid");
        f.addJavaImport("org.springframework.web.bind.annotation.PathVariable");
        f.addJavaImport("org.springframework.web.bind.annotation.PutMapping");
        f.addJavaImport("org.springframework.web.bind.annotation.RequestBody");
    }

    public void addResourceDeclaration(JavaFlow f) {
        f.L("");
        f.L____("@PutMapping(\"/", entity().apiCollectionName(), "/{id}\")");
        f.L____("public ", entity().uname, "Response ", lnameWithoutEntity(), "(@PathVariable Long id, @Valid @RequestBody ", requestName(), " request) {");
        f.L________("return ", entity().lname, "Service.", lnameWithoutEntity(), "(id, request);");
        f.L____("}");
    }

}
