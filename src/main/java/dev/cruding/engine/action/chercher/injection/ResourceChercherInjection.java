package dev.cruding.engine.action.chercher.injection;

import dev.cruding.engine.flow.JavaFlow;
import dev.cruding.engine.injection.ResourceActionInjection;

public class ResourceChercherInjection extends ResourceActionInjection {

    public void addResourceImport(JavaFlow f) {
        f.addJavaImport("org.springdoc.core.annotations.ParameterObject");
        f.addJavaImport("org.springframework.data.domain.Page");
        f.addJavaImport("org.springframework.data.domain.Pageable");
        f.addJavaImport("org.springframework.web.bind.annotation.PostMapping");
        f.addJavaImport("org.springframework.web.bind.annotation.RequestBody");
    }

    public void addResourceDeclaration(JavaFlow f) {
        f.L("");
        f.L____("@PostMapping(\"/", lcoreName(), "\")");
        f.L____("public Page<", entite().uname, "> ", lcoreName(), "(@RequestBody ", entite().uname, " ", entite().lname, ", @ParameterObject Pageable pageable) {");
        f.L________("return ", entite().lname, "Repository.", lcoreName(), "(", entite().lname, ", pageable);");
        f.L____("}");
    }
}
