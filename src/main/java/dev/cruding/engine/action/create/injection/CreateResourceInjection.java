package dev.cruding.engine.action.create.injection;

import dev.cruding.engine.action.specifique.injection.BasicResourceInjection;
import dev.cruding.engine.flow.JavaFlow;

public class CreateResourceInjection extends BasicResourceInjection {

    public void addResourceImport(JavaFlow f) {
        f.addJavaImport("org.springframework.http.HttpStatus");
        f.addJavaImport("org.springframework.http.ResponseEntity");
        f.addJavaImport("org.springframework.web.bind.annotation.PostMapping");
        f.addJavaImport("org.springframework.web.bind.annotation.RequestBody");
        f.addJavaImport("jakarta.validation.Valid");
        if (byFatherId() && entity().haveFather) {
            f.addJavaImport("org.springframework.web.bind.annotation.PathVariable");
        }
    }

    public void addResourceDeclaration(JavaFlow f) {
        f.L("");
        if (byFatherId() && entity().haveFather) {
            f.L____("@PostMapping(\"/", entity().father.referencedEntity.apiCollectionName(), "/{id", entity().ufather, "}/", entity().apiCollectionName(), "\")");
        } else {
            f.L____("@PostMapping(\"/", entity().apiCollectionName(), "\")");
        }
        f.L____("public ResponseEntity<", entity().uname, "Response> ", lnameWithoutEntity(), "(");
        if (byFatherId() && entity().haveFather) {
            f.__("@PathVariable Long id" + entity().ufather, ", ");
        }
        f.__("@Valid @RequestBody ", requestName(), " request) {");
        if (byFatherId() && entity().haveFather) {
            f.L________(entity().uname, "Response response = ", entity().lname, "Service.", lnameWithoutEntity(), "(id", entity().ufather, ", request);");
        } else {
            f.L________(entity().uname, "Response response = ", entity().lname, "Service.", lnameWithoutEntity(), "(request);");
        }
        f.L________("return ResponseEntity.status(HttpStatus.CREATED).body(response);");
        f.L____("}");
    }

}
