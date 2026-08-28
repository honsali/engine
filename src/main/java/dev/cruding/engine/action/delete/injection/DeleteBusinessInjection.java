package dev.cruding.engine.action.delete.injection;

import dev.cruding.engine.action.specifique.injection.BasicBusinessInjection;
import dev.cruding.engine.flow.JavaFlow;

public class DeleteBusinessInjection extends BasicBusinessInjection {

    public void addBusinessImport(JavaFlow f) {
        f.addJavaImport("app.core.exception.ResourceNotFoundException");
    }

    public void addBusinessDeclaration(JavaFlow f) {
        f.L("");
        f.L____("@Transactional");
        f.L____("public void ", lnameWithoutEntity(), "(Long id) {");
        f.L________(entity().uname, " ", entity().lname, " = ", entity().lname, "Repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(\"", entity().uname, "\", id));");
        f.L________(entity().lname, "Repository.delete(", entity().lname, ");");
        f.L________(entity().lname, "Repository.flush();");
        f.L____("}");
    }

}
