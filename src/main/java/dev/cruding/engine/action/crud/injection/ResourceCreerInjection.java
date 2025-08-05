package dev.cruding.engine.action.crud.injection;

import dev.cruding.engine.action.specifique.injection.ResourceSpecifiqueInjection;
import dev.cruding.engine.flow.JavaFlow;

public class ResourceCreerInjection extends ResourceSpecifiqueInjection {

    public void addResourceImport(JavaFlow f) {
        super.addResourceImport(f);
        if (entite().havePere) {
            f.addJavaImport("java.util.Optional");
            f.addJavaImport("org.springframework.http.HttpStatus");
            f.addJavaImport("org.springframework.web.server.ResponseStatusException");
        }
    }

    public void addResourceDeclaration(JavaFlow f) {
        f.L("");
        f.L____("@", urest(), "Mapping");
        if ((parIdGrandPere() && entite().haveGrandPere) || (parIdPere() && entite().havePere) || parId()) {
            f.__("(\"");
        }
        if (parIdGrandPere() && entite().haveGrandPere) {
            f.__("/", entite().lgrandPere, "/{id" + entite().ugrandPere, "}");
        }
        if (parIdPere() && entite().havePere) {
            f.__("/", entite().lpere, "/{id" + entite().upere, "}");
        }
        if (parId()) {
            f.__("/${id", entite().uname, "}");
        }
        if ((parIdGrandPere() && entite().haveGrandPere) || (parIdPere() && entite().havePere) || parId()) {
            f.__("\")");
        }
        f.L____("public ResponseEntity<", entite().uname, "Dto> ", lnameSansEntite(), "(");
        if (parIdGrandPere() && entite().haveGrandPere) {
            f.__("@PathVariable Long id" + entite().ugrandPere, ",");
        }
        if (parIdPere() && entite().havePere) {
            f.__("@PathVariable Long id" + entite().upere, ", ");
        }
        f.__("@Valid @RequestBody ", entite().uname, " ", entite().lname);
        f.__(") throws URISyntaxException {");
        if (entite().havePere) {
            f.L________("Optional<", entite().upere, "> ", entite().lpere, " = ", entite().lpere, "Repository.findById(id", entite().upere, ");");
            f.L________("if (!", entite().lpere, ".isPresent()) {");
            f.L____________("throw new ResponseStatusException(HttpStatus.NOT_FOUND, \"", entite().lpere, ".notFoud\");");
            f.L________("}");
            f.L________(entite().lname, ".set", entite().upere, "(", entite().lpere, ".get());");
        }
        f.L________(entite().uname, " result = ", entite().lname, "Repository.save(", entite().lname, ");");
        f.L________("return ResponseEntity.ok().body(", entite().uname, "Dto.asEntity(result));");
        f.L____("}");
    }

}
