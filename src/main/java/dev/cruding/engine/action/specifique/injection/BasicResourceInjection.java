package dev.cruding.engine.action.specifique.injection;

import dev.cruding.engine.flow.JavaFlow;
import dev.cruding.engine.injection.ActionResourceInjection;

public class BasicResourceInjection extends ActionResourceInjection {

    public void addResourceDeclaration(JavaFlow flow) {}

    public void addResourceImport(JavaFlow f) {
        f.addJavaImport("org.springframework.http.ResponseEntity");
        f.addJavaImport("jakarta.validation.Valid");
        f.addJavaImport("org.springframework.web.bind.annotation.RequestBody");
        f.addJavaImport("org.springframework.web.bind.annotation." + urest() + "Mapping");

    }
}
