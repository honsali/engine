package dev.cruding.engine.action.crud.injection;

import dev.cruding.engine.action.specifique.injection.ResourceSpecifiqueInjection;
import dev.cruding.engine.flow.JavaFlow;

public class ResourceSupprimerInjection extends ResourceSpecifiqueInjection {



    public void addResourceDeclaration(JavaFlow f) {
        f.L("");
        f.L____("@DeleteMapping(\"/{id}\")");
        f.L____("public ResponseEntity<Void> ", lnameAvecEntite(), "(@PathVariable Long id");
        f.__(") throws URISyntaxException {");
        f.L________(entite().lname, "Repository.deleteById(id);");
        f.L________("return ResponseEntity.noContent().build();");
        f.L____("}");
    }

}
