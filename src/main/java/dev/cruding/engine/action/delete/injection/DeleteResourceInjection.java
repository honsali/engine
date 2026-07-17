package dev.cruding.engine.action.delete.injection;

import dev.cruding.engine.action.specifique.injection.BasicResourceInjection;
import dev.cruding.engine.flow.JavaFlow;

public class DeleteResourceInjection extends BasicResourceInjection {



    public void addResourceDeclaration(JavaFlow f) {
        f.L("");
        f.L____("@DeleteMapping(\"/", entity().lname, "/{id}\")");
        f.L____("public ResponseEntity<Void> ", lcoreName(), "(@PathVariable Long id) {");
        f.L________(entity().lname, "Service.supprimer(id);");
        f.L________("return ResponseEntity.noContent().build();");
        f.L____("}");
    }

}
