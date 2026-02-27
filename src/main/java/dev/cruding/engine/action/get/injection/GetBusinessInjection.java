package dev.cruding.engine.action.get.injection;

import dev.cruding.engine.flow.JavaFlow;
import dev.cruding.engine.injection.ActionBusinessInjection;

public class GetBusinessInjection extends ActionBusinessInjection {



    public void addBusinessImport(JavaFlow f) {
        f.addJavaImport("java.util.Optional");

    }

    public void addBusinessDeclaration(JavaFlow f) {
        f.L("");
        f.L____("@Transactional(readOnly = true)");
        f.L____("public Optional<", entity().uname, "Dto> ", lnameWithoutEntity(), "(", entity().id_.jtype, " ", byField()[0].lname, ") {");
        f.L________("return ", entity().lname, "Repository.findById(id).map(", entity().uname, "Dto::toDto);");
        f.L____("}");
    }

}
