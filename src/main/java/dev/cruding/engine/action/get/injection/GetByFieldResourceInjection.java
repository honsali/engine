package dev.cruding.engine.action.get.injection;

import dev.cruding.engine.flow.JavaFlow;
import dev.cruding.engine.injection.ActionResourceInjection;

public class GetByFieldResourceInjection extends ActionResourceInjection {



    public void addResourceImport(JavaFlow f) {
        f.addJavaImport("org.springframework.http.HttpStatus");
        f.addJavaImport("org.springframework.http.ResponseEntity");
        f.addJavaImport("org.springframework.web.bind.annotation.GetMapping");
        f.addJavaImport("org.springframework.web.bind.annotation.PathVariable");
        f.addJavaImport("org.springframework.web.server.ResponseStatusException");

    }

    public void addResourceDeclaration(JavaFlow f) {
        f.L("");
        f.L____("@GetMapping(\"/", entity().lname, "/{" + byField()[0].lname + "}\")");
        f.L____("public ", entity().uname, "Dto ", lnameWithoutEntity(), "(@PathVariable ", entity().id_.jtype, " ", byField()[0].lname, ") {");
        f.L________("return ", entity().lname, "Service.", lnameWithoutEntity(), "(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, \"", entity().uname, " not found\"));");
        f.L____("}");
    }

}
