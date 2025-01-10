package dev.cruding.engine.action.crud.injection;

import dev.cruding.engine.action.specifique.injection.ResourceSpecifiqueInjection;
import dev.cruding.engine.flow.JavaFlow;

public class ResourceEnregistrerInjection extends ResourceSpecifiqueInjection {

    public void addResourceDeclaration(JavaFlow f) {
        f.L("");
        f.L____("@", urest(), "Mapping");
        if ((parIdGrandPere() && entite().haveGrandPere) || (parIdPere() && entite().havePere) || byId()) {
            f.__("(\"");
        }
        if (parIdGrandPere() && entite().haveGrandPere) {
            f.__("/", entite().lgrandPere, "/{id" + entite().ugrandPere, "}");
        }
        if (parIdPere() && entite().havePere) {
            f.__("/", entite().lpere, "/{id" + entite().upere, "}");
        }
        if (byId()) {
            f.__("/${id", entite().uname, "}");
        }
        if ((parIdGrandPere() && entite().haveGrandPere) || (parIdPere() && entite().havePere) || byId()) {
            f.__("\")");
        }
        f.L____("public ResponseEntity<Void> ", lcoreName(), "(");
        if (parIdGrandPere() && entite().haveGrandPere) {
            f.__("@PathVariable Long id" + entite().ugrandPere, ",");
        }
        if (parIdPere() && entite().havePere) {
            f.__("@PathVariable Long id" + entite().upere, ", ");
        }
        f.__("@Valid @RequestBody ", entite().uname, " ", entite().lname);
        f.__(") throws URISyntaxException {");
        f.L________(entite().lname, "Repository.save(", entite().lname, ");");
        f.L________("return ResponseEntity.noContent().build();");
        f.L____("}");
    }

}
