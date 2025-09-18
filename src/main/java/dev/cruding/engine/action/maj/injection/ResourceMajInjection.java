package dev.cruding.engine.action.maj.injection;

import dev.cruding.engine.action.specifique.injection.ResourceSpecifiqueInjection;
import dev.cruding.engine.flow.JavaFlow;

public class ResourceMajInjection extends ResourceSpecifiqueInjection {

    public void addResourceDeclaration(JavaFlow f) {
        f.L("");
        f.L____("@", urest(), "Mapping(\"/api");
        if ((parIdPere() && entite().havePere) || parId()) {
            f.__("(\"/", entite().lpere, "/{id" + entite().upere, "}");
        }
        f.__("/", entite().lname, "/{id}\")");
        f.L____("public ResponseEntity<", entite().uname, "Dto> ", lnameSansEntite(), "(");
        if (parIdPere() && entite().havePere) {
            f.__("@PathVariable Long id" + entite().upere, ", ");
        }
        f.__("@PathVariable Long id, @Valid @RequestBody ", entite().uname, "Dto ", entite().lname, "Dto) throws URISyntaxException {");
        f.L________("try {");
        f.L____________("if (", entite().lname, "Dto.id() != null && !", entite().lname, "Dto.id().equals(id)) {");
        f.L________________("throw new ResponseStatusException(HttpStatus.BAD_REQUEST, \"Path ID and body ID mismatch\");");
        f.L____________("}");
        f.L____________(entite().uname, "Dto result = ", entite().lname, "Service.maj(id, ", entite().lname, "Dto);");
        f.L____________("return ResponseEntity.ok().body(result);");
        f.L________("} catch (IllegalArgumentException e) {");
        f.L____________("throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());");
        f.L________("}");
        f.L____("}");
    }

}
