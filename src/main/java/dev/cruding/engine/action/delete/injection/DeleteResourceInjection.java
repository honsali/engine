package dev.cruding.engine.action.delete.injection;

import dev.cruding.engine.action.specifique.injection.BasicResourceInjection;
import dev.cruding.engine.flow.JavaFlow;

public class DeleteResourceInjection extends BasicResourceInjection {

    public void addResourceImport(JavaFlow f) {
        f.addJavaImport("org.springframework.http.ResponseEntity");
        f.addJavaImport("org.springframework.web.bind.annotation.DeleteMapping");
        f.addJavaImport("org.springframework.web.bind.annotation.PathVariable");
    }

    public void addResourceDeclaration(JavaFlow f) {
        f.L("");
        f.L____("@DeleteMapping(\"/{id}\")");
        f.L____("public ResponseEntity<Void> ", lnameWithoutEntity(), "(@PathVariable Long id) {");
        f.L________(entity().lname, "Service.", lnameWithoutEntity(), "(id);");
        f.L________("return ResponseEntity.noContent().build();");
        f.L____("}");
    }

}
