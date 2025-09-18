package dev.cruding.engine.action.supprimer.injection;

import dev.cruding.engine.action.specifique.injection.BusinessSpecifiqueInjection;
import dev.cruding.engine.flow.JavaFlow;

public class BusinessSupprimerInjection extends BusinessSpecifiqueInjection {


    public void addBusinessDeclaration(JavaFlow f) {
        f.L("");
        f.L____("public void ", lcoreName(), "(Long id) {");
        f.L________("if (!", entite().lname, "Repository.existsById(id)) {");
        f.L____________("throw new IllegalArgumentException(\"", entite().uname, " not found\");");
        f.L________("}");
        f.L________(entite().lname, "Repository.deleteById(id);");
        f.L____("}");
    }

}
