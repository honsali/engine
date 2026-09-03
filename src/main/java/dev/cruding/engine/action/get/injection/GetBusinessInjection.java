package dev.cruding.engine.action.get.injection;

import dev.cruding.engine.flow.JavaFlow;
import dev.cruding.engine.injection.ActionBusinessInjection;

public class GetBusinessInjection extends ActionBusinessInjection {

    public boolean requiresEntityResolver() {
        return true;
    }

    public void addBusinessDeclaration(JavaFlow f) {
        f.L("");
        f.L____("@Transactional(readOnly = true)");
        f.L____("public ", entity().uname, "Response ", lnameWithoutEntity(), "(", byField()[0].jtype, " ", byField()[0].lname, ") {");
        if (entity().listRef().isEmpty()) {
            f.L________("return ", entity().uname, "Mapper.toResponse(recuperer", entity().uname, "(", byField()[0].lname, "));");
        } else {
            f.L________(entity().uname, " ", entity().lname, " = recuperer", entity().uname, "(", byField()[0].lname, ");");
            f.L________("return ", entity().uname, "Mapper.toResponse(", entity().lname, ");");
        }
        f.L____("}");
    }

}
