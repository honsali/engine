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
        if ((byGrandPereId() && entite().haveGrandPere) || (byPereId() && entite().havePere) || byId()) {
            f.__("(\"");
        }
        if (byGrandPereId() && entite().haveGrandPere) {
            f.__("/", entite().lgrandPere, "/{id" + entite().ugrandPere, "}");
        }
        if (byPereId() && entite().havePere) {
            f.__("/", entite().lpere, "/{id" + entite().upere, "}");
        }
        if (byId()) {
            f.__("/${id", entite().uname, "}");
        }
        if ((byGrandPereId() && entite().haveGrandPere) || (byPereId() && entite().havePere) || byId()) {
            f.__("\")");
        }
        f.L____("public ResponseEntity<Void> ", lcoreName(), "(");
        if (byGrandPereId() && entite().haveGrandPere) {
            f.__("@PathVariable Long id" + entite().ugrandPere, ",");
        }
        if (byPereId() && entite().havePere) {
            f.__("@PathVariable Long id" + entite().upere, ", ");
        }
        f.__("@Valid @RequestBody ", entite().uname, " ", entite().lname);
        f.__(") throws URISyntaxException {");
        f.L________(entite().lname, "Repository.save(", entite().lname, ");");
        f.L________("return ResponseEntity.noContent().build();");
        f.L____("}");
    }


}
