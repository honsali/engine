package dev.cruding.engine.action.create.injection;

import dev.cruding.engine.action.specifique.injection.BasicResourceInjection;
import dev.cruding.engine.flow.JavaFlow;

public class CreateResourceInjection extends BasicResourceInjection {

    public void addResourceImport(JavaFlow f) {
        super.addResourceImport(f);
        f.addJavaImport("org.springframework.http.HttpStatus");
        f.addJavaImport("org.springframework.http.ResponseEntity");
        f.addJavaImport("org.springframework.web.server.ResponseStatusException");
        f.addJavaImport("org.springframework.web.bind.annotation.PostMapping");
        f.addJavaImport("org.springframework.web.bind.annotation.RequestBody");
        f.addJavaImport("java.util.NoSuchElementException");
        f.addJavaImport("jakarta.validation.Valid");
        if (entity().haveFather) {
            f.addJavaImport("org.springframework.web.bind.annotation.PathVariable");
        }
    }

    public void addResourceDeclaration(JavaFlow f) {
        f.L("");
        f.L____("@PostMapping(\"/api");
        if ((byFatherId() && entity().haveFather) || byId()) {
            f.__("/", entity().lfather, "/{id" + entity().ufather, "}");
        }
        f.__("/", entity().lname, "\")");
        f.L____("public ResponseEntity<", entity().uname, "Dto> ", lnameWithoutEntity(), "(");
        if (byFatherId() && entity().haveFather) {
            f.__("@PathVariable Long id" + entity().ufather, ", ");
        }
        f.__("@Valid @RequestBody ", entity().uname, "Dto ", entity().lname + "Dto) {");
        f.L________("try {");
        if (entity().haveFather) {
            f.L____________(entity().uname, "Dto result = ", entity().lname, "Service.creer(id", entity().ufather, ", ", entity().lname, "Dto);");

        } else {
            f.L____________(entity().uname, "Dto result = ", entity().lname, "Service.creer(", entity().lname, "Dto);");
        }
        f.L____________("return ResponseEntity.status(HttpStatus.CREATED).body(result);");
        f.L________("} catch (NoSuchElementException e) {");
        f.L____________("throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());");
        f.L________("} catch (IllegalArgumentException e) {");
        f.L____________("throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());");
        f.L________("}");
        f.L____("}");
    }

}
