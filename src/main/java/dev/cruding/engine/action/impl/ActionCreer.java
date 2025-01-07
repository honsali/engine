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
        f.L____("public ResponseEntity<", entite().uname, "Dto> ", lcoreName(), "(");
        if (byGrandPereId() && entite().haveGrandPere) {
            f.__("@PathVariable Long id" + entite().ugrandPere, ",");
        }
        if (byPereId() && entite().havePere) {
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
