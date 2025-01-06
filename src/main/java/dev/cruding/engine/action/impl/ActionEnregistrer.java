package dev.cruding.engine.action.impl;

import dev.cruding.engine.flow.JavaFlow;

public class ActionEnregistrer extends ActionSpecifique {



    public String lrest() {
        return "put";
    }

    public String urest() {
        return "Put";
    }

    public void addResourceDeclaration(JavaFlow f) {
        f.L("");
        f.L____("@", urest(), "Mapping");
        if ((byGrandFatherId() && entite().haveGrandFather) || (byFatherId() && entite().haveFather) || byId()) {
            f.__("(\"");
        }
        if (byGrandFatherId() && entite().haveGrandFather) {
            f.__("/", entite().lgrandfather, "/{id" + entite().ugrandfather, "}");
        }
        if (byFatherId() && entite().haveFather) {
            f.__("/", entite().lfather, "/{id" + entite().ufather, "}");
        }
        if (byId()) {
            f.__("/${id", entite().uname, "}");
        }
        if ((byGrandFatherId() && entite().haveGrandFather) || (byFatherId() && entite().haveFather) || byId()) {
            f.__("\")");
        }
        f.L____("public ResponseEntity<Void> ", lcoreName(), "(");
        if (byGrandFatherId() && entite().haveGrandFather) {
            f.__("@PathVariable Long id" + entite().ugrandfather, ",");
        }
        if (byFatherId() && entite().haveFather) {
            f.__("@PathVariable Long id" + entite().ufather, ", ");
        }
        f.__("@Valid @RequestBody ", entite().uname, " ", entite().lname);
        f.__(") throws URISyntaxException {");
        f.L________(entite().lname, "Repository.save(", entite().lname, ");");
        f.L________("return ResponseEntity.noContent().build();");
        f.L____("}");
    }


}
