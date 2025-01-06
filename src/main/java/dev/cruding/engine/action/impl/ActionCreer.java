package dev.cruding.engine.action.impl;

import dev.cruding.engine.flow.JavaFlow;

public class ActionCreer extends ActionSpecifique {



    public String lrest() {
        return "post";
    }

    public String urest() {
        return "Post";
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
        f.L____("public ResponseEntity<", entite().uname, "Dto> ", lcoreName(), "(");
        if (byGrandFatherId() && entite().haveGrandFather) {
            f.__("@PathVariable Long id" + entite().ugrandfather, ",");
        }
        if (byFatherId() && entite().haveFather) {
            f.__("@PathVariable Long id" + entite().ufather, ", ");
        }

        f.__("@Valid @RequestBody ", entite().uname, " ", entite().lname);
        f.__(") throws URISyntaxException {");
        if (entite().haveFather) {
            f.L________("Optional<", entite().ufather, "> ", entite().lfather, " = ", entite().lfather, "Repository.findById(id", entite().ufather, ");");
            f.L________("if (!", entite().lfather, ".isPresent()) {");
            f.L____________("throw new ResponseStatusException(HttpStatus.NOT_FOUND, \"", entite().lfather, ".notFoud\");");
            f.L________("}");
            f.L________(entite().lname, ".set", entite().ufather, "(", entite().lfather, ".get());");
        }
        f.L________(entite().uname, " result = ", entite().lname, "Repository.save(", entite().lname, ");");
        f.L________("return ResponseEntity.ok().body(", entite().uname, "Dto.asEntity(result));");
        f.L____("}");
    }

}
