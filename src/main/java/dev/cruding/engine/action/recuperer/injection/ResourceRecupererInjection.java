package dev.cruding.engine.action.recuperer.injection;

import dev.cruding.engine.flow.JavaFlow;
import dev.cruding.engine.injection.ResourceActionInjection;

public class ResourceRecupererInjection extends ResourceActionInjection {



    public void addResourceImport(JavaFlow f) {
        f.addJavaImport("org.springframework.http.HttpStatus");
        f.addJavaImport("org.springframework.http.ResponseEntity");
        f.addJavaImport("org.springframework.web.bind.annotation.GetMapping");
        f.addJavaImport("org.springframework.web.bind.annotation.PathVariable");
        f.addJavaImport("org.springframework.web.server.ResponseStatusException");

    }

    public void addResourceDeclaration(JavaFlow f) {
        f.L("");
        f.L____("@GetMapping(\"/api/", entite().lname, "/{" + parChamp()[0].lname + "}\")");
        f.L____("public ResponseEntity<", entite().uname, "Dto> ", lnameSansEntite(), "(@PathVariable ", entite().id_.jtype, " ", parChamp()[0].lname, ") {");
        f.L________("return ", entite().lname, "Service.", lnameSansEntite(), "(id).map(dto -> ResponseEntity.ok().body(dto)).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, \"", entite().uname, " not found\"));");
        f.L____("}");
    }

}
