package dev.cruding.engine.action.update.injection;

import dev.cruding.engine.action.specifique.injection.BasicResourceInjection;
import dev.cruding.engine.flow.JavaFlow;

public class UpdateResourceInjection extends BasicResourceInjection {

    public void addResourceDeclaration(JavaFlow f) {
        f.L("");
        f.L____("@", urest(), "Mapping(\"/api");
        if ((byFatherId() && entity().haveFather) || byId()) {
            f.__("(\"/", entity().lfather, "/{id" + entity().ufather, "}");
        }
        f.__("/", entity().lname, "/{id}\")");
        f.L____("public ResponseEntity<", entity().uname, "Dto> ", lnameWithoutEntity(), "(");
        if (byFatherId() && entity().haveFather) {
            f.__("@PathVariable Long id" + entity().ufather, ", ");
        }
        f.__("@PathVariable Long id, @Valid @RequestBody ", entity().uname, "Dto ", entity().lname, "Dto) throws URISyntaxException {");
        f.L________("try {");
        f.L____________("if (", entity().lname, "Dto.id() != null && !", entity().lname, "Dto.id().equals(id)) {");
        f.L________________("throw new ResponseStatusException(HttpStatus.BAD_REQUEST, \"Path ID and body ID mismatch\");");
        f.L____________("}");
        f.L____________(entity().uname, "Dto result = ", entity().lname, "Service.maj(id, ", entity().lname, "Dto);");
        f.L____________("return ResponseEntity.ok().body(result);");
        f.L________("} catch (IllegalArgumentException e) {");
        f.L____________("throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());");
        f.L________("}");
        f.L____("}");
    }

}
