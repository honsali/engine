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
        if (entity().haveFather) {
            f.L________("Optional<", entity().ufather, "Ref> ", entity().lfather, " = ", entity().lfather, "RefRepository.findById(id", entity().ufather, ");");
            f.L________("if (!", entity().lfather, ".isPresent()) {");
            f.L____________("throw new ResponseStatusException(HttpStatus.NOT_FOUND, \"", entity().lfather, ".notFoud\");");
            f.L________("}");
            f.L________(entity().lname, ".set", entity().ufather, "(", entity().lfather, ".get());");
        }
        f.L________(entity().uname, " result = ", entity().lname, "Repository.save(", entity().lname, ");");
        f.L________("return ResponseEntity.ok().body(result);");
        f.L____("}");
    }

}
