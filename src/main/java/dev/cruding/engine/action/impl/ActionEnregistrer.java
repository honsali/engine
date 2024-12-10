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
        if ((byGrandFatherId() && entity().haveGrandFather) || (byFatherId() && entity().haveFather) || byId()) {
            f.__("(\"");
        }
        if (byGrandFatherId() && entity().haveGrandFather) {
            f.__("/", entity().lgrandfather, "/{id" + entity().ugrandfather, "}");
        }
        if (byFatherId() && entity().haveFather) {
            f.__("/", entity().lfather, "/{id" + entity().ufather, "}");
        }
        if (byId()) {
            f.__("/${id", entity().uname, "}");
        }
        if ((byGrandFatherId() && entity().haveGrandFather) || (byFatherId() && entity().haveFather) || byId()) {
            f.__("\")");
        }
        f.L____("public ResponseEntity<", entity().uname, "> ", lcoreName(), "(");
        if (byGrandFatherId() && entity().haveGrandFather) {
            f.__("@PathVariable Long id" + entity().ugrandfather, ",");
        }
        if (byFatherId() && entity().haveFather) {
            f.__("@PathVariable Long id" + entity().ufather, ", ");
        }

        f.__("@Valid @RequestBody ", entity().uname, " ", entity().lname);
        f.__(") throws URISyntaxException {");
        f.L________(entity().uname, " result = ", entity().lname, "Repository.save(", entity().lname, ");");
        f.L________("return ResponseEntity.ok().body(result);");
        f.L____("}");
    }


}
