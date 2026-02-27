package dev.cruding.engine.action.delete.injection;

import dev.cruding.engine.action.specifique.injection.BasicBusinessInjection;
import dev.cruding.engine.flow.JavaFlow;

public class DeleteBusinessInjection extends BasicBusinessInjection {


    public void addBusinessDeclaration(JavaFlow f) {
        f.L("");
        f.L____("public void ", lcoreName(), "(Long id) {");
        f.L________("if (!", entity().lname, "Repository.existsById(id)) {");
        f.L____________("throw new IllegalArgumentException(\"", entity().uname, " not found\");");
        f.L________("}");
        f.L________(entity().lname, "Repository.deleteById(id);");
        f.L____("}");
    }

}
