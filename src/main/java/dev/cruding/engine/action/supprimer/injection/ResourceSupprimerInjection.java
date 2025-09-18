package dev.cruding.engine.action.supprimer.injection;

import dev.cruding.engine.action.specifique.injection.ResourceSpecifiqueInjection;
import dev.cruding.engine.flow.JavaFlow;

public class ResourceSupprimerInjection extends ResourceSpecifiqueInjection {



    public void addResourceDeclaration(JavaFlow f) {
        f.L("");
        f.L____("@DeleteMapping(\"/api/", entite().lname, "/{id}\")");
        f.L____("public ResponseEntity<Void> ", lcoreName(), "(@PathVariable Long id) {");
        f.L________("try {");
        f.L____________(entite().lname, "Service.supprimer(id);");
        f.L____________("return ResponseEntity.noContent().build();");
        f.L________("} catch (IllegalArgumentException e) {");
        f.L____________("throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());");
        f.L________("}");
        f.L____("}");
    }

}
