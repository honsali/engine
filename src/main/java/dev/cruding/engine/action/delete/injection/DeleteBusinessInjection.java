package dev.cruding.engine.action.delete.injection;

import dev.cruding.engine.action.specifique.injection.BasicBusinessInjection;
import dev.cruding.engine.flow.JavaFlow;

public class DeleteBusinessInjection extends BasicBusinessInjection {


    public void addBusinessDeclaration(JavaFlow f) {
        f.L("");
        f.L____("public void ", lcoreName(), "(Long id) {");
        f.L________(entity().uname, " ", entity().lname, " = ", entity().lname, "Repository.findById(id).orElseThrow(() -> new NoSuchElementException(\"", entity().uname, " not found\"));");
        f.L________(entity().lname, "Repository.delete(", entity().lname, ");");
        f.L____("}");
    }

}
