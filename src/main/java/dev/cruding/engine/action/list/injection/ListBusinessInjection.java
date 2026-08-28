package dev.cruding.engine.action.list.injection;

import java.util.List;
import dev.cruding.engine.field.Field;
import dev.cruding.engine.flow.JavaFlow;
import dev.cruding.engine.injection.ActionBusinessInjection;

public class ListBusinessInjection extends ActionBusinessInjection {

    public List<Field> businessRelationFields() {
        if (byFatherId() && entity().haveFather) {
            return List.of(entity().father);
        }
        return List.of();
    }

    public void addBusinessImport(JavaFlow f) {
        f.addJavaImport("java.util.List");
        if (byFatherId() && entity().haveFather) {
            f.addJavaImport("app.core.exception.ResourceNotFoundException");
        }
    }

    public void addBusinessDeclaration(JavaFlow f) {
        f.L("");
        f.L____("@Transactional(readOnly = true)");
        f.L____("public List<", entity().uname, "Response> ", lnameWithoutEntity(), "(");
        if (byFatherId() && entity().haveFather) {
            f.__("Long id", entity().ufather);
        }
        f.__(") {");
        if (byFatherId() && entity().haveFather) {
            f.L________("if (!", entity().lfather, "Repository.existsById(id", entity().ufather, ")) {");
            f.L____________("throw new ResourceNotFoundException(\"", entity().father.jtype, "\", id", entity().ufather, ");");
            f.L________("}");
            f.L________("return ", entity().lname, "Repository.findAllBy", entity().ufather, "IdOrderBy", orderBy(), "(id", entity().ufather, ").stream().map(", entity().uname, "Mapper::toResponse).toList();");
        } else {
            f.L________("return ", entity().lname, "Repository.findAllByOrderBy", orderBy(), "().stream().map(", entity().uname, "Mapper::toResponse).toList();");
        }
        f.L____("}");
    }
}
