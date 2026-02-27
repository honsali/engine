package dev.cruding.engine.action.list.injection;

import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.flow.JavaFlow;
import dev.cruding.engine.gen.Context;
import dev.cruding.engine.injection.ActionBusinessInjection;

public class ListBusinessInjection extends ActionBusinessInjection {

    public void addBusinessImport(JavaFlow f) {
        f.addJavaImport("java.util.List");
        f.addJavaImport("java.util.stream.Collectors");
        if (entity().haveFather) {
            Entity fe = Context.getInstance().getEntity(entity().father.jtype);
            f.addJavaImport("app.domain." + fe.pkg + "." + fe.lname + "." + fe.uname + "Repository");
        }
    }

    public void addBusinessDeclaration(JavaFlow f) {
        f.L("");
        f.L____("@Transactional(readOnly = true)");
        f.L____("public List<", entity().uname, "Dto> ", lnameWithoutEntity(), "(");

        if (byFatherId() && entity().haveFather) {
            f.__("Long id", entity().ufather);
        }
        f.__(") {");

        if (byFatherId() && entity().haveFather) {
            f.L________("if (!employeRepository.existsById(id", entity().ufather, ")) {");
            f.L____________("throw new IllegalArgumentException(\"", entity().ufather, " not found\");");
            f.L________("}");
        }
        f.L________("return ", entity().lname, "Repository.findAllBy");

        if (byFatherId() && entity().haveFather) {
            f.__(entity().ufather, "_Id");
        }

        f.__("OrderBy", orderBy(), "(");

        if (byFatherId() && entity().haveFather) {
            f.__("id", entity().ufather);
        }
        f.__(").stream().map(", entity().uname, "Dto::toDto).collect(Collectors.toList());");
        f.L____("}");
    }
}
