package dev.cruding.engine.action.specifique.injection;

import dev.cruding.engine.flow.JavaFlow;
import dev.cruding.engine.injection.ResourceActionInjection;

public class ResourceSpecifiqueInjection extends ResourceActionInjection {

    public void addResourceDeclaration(JavaFlow flow) {}

    public void addResourceImport(JavaFlow f) {
        f.addJavaImport("org.springframework.http.ResponseEntity");
        f.addJavaImport("jakarta.validation.Valid");
        f.addJavaImport("org.springframework.web.bind.annotation.RequestBody");
        f.addJavaImport("java.net.URISyntaxException");
        f.addJavaImport("org.springframework.web.bind.annotation." + urest() + "Mapping");

    }
}
