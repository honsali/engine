package dev.cruding.engine.action.impl;

import dev.cruding.engine.flow.JavaFlow;

public class ActionSupprimer extends ActionSpecifique {

    public String lrest() {
        return "delete";
    }

    public String urest() {
        return "Delete";
    }


    public void addResourceDeclaration(JavaFlow f) {
        f.L("");
        f.L____("@DeleteMapping(\"/{id}\")");
        f.L____("public ResponseEntity<Void> ", lname(), "(@PathVariable Long id");
        f.__(") throws URISyntaxException {");
        f.L________(entite().lname, "Repository.deleteById(id);");
        f.L________("return ResponseEntity.noContent().build();");
        f.L____("}");
    }


}
