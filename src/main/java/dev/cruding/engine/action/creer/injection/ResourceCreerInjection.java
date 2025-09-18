package dev.cruding.engine.action.creer.injection;

import dev.cruding.engine.action.specifique.injection.ResourceSpecifiqueInjection;
import dev.cruding.engine.flow.JavaFlow;

public class ResourceCreerInjection extends ResourceSpecifiqueInjection {

    public void addResourceImport(JavaFlow f) {
        super.addResourceImport(f);
        f.addJavaImport("org.springframework.http.HttpStatus");
        f.addJavaImport("org.springframework.http.ResponseEntity");
        f.addJavaImport("org.springframework.web.server.ResponseStatusException");
        f.addJavaImport("org.springframework.web.bind.annotation.PostMapping");
        f.addJavaImport("org.springframework.web.bind.annotation.RequestBody");
        f.addJavaImport("java.net.URISyntaxException");
        f.addJavaImport("jakarta.validation.Valid");
        if (entite().havePere) {
            f.addJavaImport("org.springframework.web.bind.annotation.PathVariable");
            f.addJavaImport("java.util.Optional");
        }
    }

    public void addResourceDeclaration(JavaFlow f) {
        f.L("");
        f.L____("@PostMapping(\"/api");
        if ((parIdPere() && entite().havePere) || parId()) {
            f.__("/", entite().lpere, "/{id" + entite().upere, "}\")");
        }
        f.__("/", entite().lname, "\")");
        f.L____("public ResponseEntity<", entite().uname, "Dto> ", lnameSansEntite(), "(");
        if (parIdPere() && entite().havePere) {
            f.__("@PathVariable Long id" + entite().upere, ", ");
        }
        f.__("@Valid @RequestBody ", entite().uname, "Dto ", entite().lname + "Dto) throws URISyntaxException {");
        if (entite().havePere) {
            f.L________("Optional<", entite().upere, "> ", entite().lpere, " = ", entite().lpere, "Service.findById(id", entite().upere, ");");
            f.L________("if (!", entite().lpere, ".isPresent()) {");
            f.L____________("throw new ResponseStatusException(HttpStatus.NOT_FOUND, \"", entite().lpere, ".notFoud\");");
            f.L________("}");
            f.L________(entite().lname, ".set", entite().upere, "(", entite().lpere, ".get());");
        }
        f.L________("try {");
        f.L____________(entite().uname, "Dto result = ", entite().lname, "Service.creer(", entite().lname, "Dto);");
        f.L____________("return ResponseEntity.status(HttpStatus.CREATED).body(result);");
        f.L________("} catch (IllegalArgumentException e) {");
        f.L____________("throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());");
        f.L________("}");
        f.L____("}");
    }

}
