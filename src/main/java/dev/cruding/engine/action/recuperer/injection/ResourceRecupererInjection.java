package dev.cruding.engine.action.recuperer.injection;

import dev.cruding.engine.flow.JavaFlow;
import dev.cruding.engine.injection.ResourceActionInjection;

public class ResourceRecupererInjection extends ResourceActionInjection {

    public String lnameField;
    public String unameField;

    public ResourceRecupererInjection(String lnameField, String unameField) {
        this.lnameField = lnameField;
        this.unameField = unameField;
    }


    public void addResourceImport(JavaFlow f) {
        f.addJavaImport("java.util.Optional");
        f.addJavaImport("org.springframework.http.HttpStatus");
        f.addJavaImport("org.springframework.http.ResponseEntity");
        f.addJavaImport("org.springframework.web.bind.annotation.GetMapping");
        f.addJavaImport("org.springframework.web.bind.annotation.PathVariable");
        f.addJavaImport("org.springframework.web.server.ResponseStatusException");

    }

    public void addResourceDeclaration(JavaFlow f) {
        f.L("");
        f.L____("@GetMapping(\"/{" + lnameField + "}\")");
        f.L____("public ResponseEntity<", entite().uname, "Dto> ", lcoreName(), "(@PathVariable ", entite().id_.jtype, " ", lnameField, ") {");
        f.L________("Optional<", entite().uname, "> ", entite().lname, " = ", entite().lname, "Repository.findById(id);");
        f.L________("return ", entite().lname, ".map(response -> ResponseEntity.ok().body(", entite().uname, "Dto.asEntity(response))).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));");
        f.L____("}");
    }

}
