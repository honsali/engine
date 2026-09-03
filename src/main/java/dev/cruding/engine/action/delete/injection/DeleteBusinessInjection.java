package dev.cruding.engine.action.delete.injection;

import dev.cruding.engine.action.specifique.injection.BasicBusinessInjection;
import dev.cruding.engine.flow.JavaFlow;

public class DeleteBusinessInjection extends BasicBusinessInjection {

    public boolean requiresEntityResolver() {
        return true;
    }

    public void addBusinessDeclaration(JavaFlow f) {
        f.L("");
        f.L____("@Transactional");
        f.L____("public void ", lnameWithoutEntity(), "(Long id) {");
        f.L________(entity().uname, " ", entity().lname, " = recuperer", entity().uname, "(id);");
        f.L________(entity().lname, "Repository.delete(", entity().lname, ");");
        f.L________(entity().lname, "Repository.flush();");
        f.L____("}");
    }

}
