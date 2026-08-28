package dev.cruding.engine.action.create.injection;

import java.util.List;
import dev.cruding.engine.action.specifique.injection.BasicBusinessInjection;
import dev.cruding.engine.field.Field;
import dev.cruding.engine.flow.JavaFlow;

public class CreateBusinessInjection extends BasicBusinessInjection {

    public List<Field> businessRelationFields() {
        if (byFatherId() && entity().haveFather) {
            return entity().listRefAndFather();
        }
        return entity().listRef();
    }

    public void addBusinessImport(JavaFlow f) {
        for (Field field : entity().fieldList) {
            if (field.isId) {
                f.addJavaImport("app.core.exception.FieldConflictException");
            }
        }
    }

    public void addBusinessDeclaration(JavaFlow f) {
        f.L("");
        f.L____("@Transactional");
        f.L____("public ", entity().uname, "Response ", lnameWithoutEntity(), "(");
        if (byFatherId() && entity().haveFather) {
            f.__("Long id", entity().ufather, ", ");
        }
        f.__(requestName(), " request) {");
        for (Field field : entity().fieldList) {
            if (field.isId) {
                f.L________("if (", entity().lname, "Repository.existsBy", field.uname, "(request.", field.lname, "())) {");
                f.L____________("throw new FieldConflictException(\"", entity().uname, "\", \"", field.lname, "\", request.", field.lname, "());");
                f.L________("}");
            }
        }
        addBusinessRelationDeclarations(f);
        f.L________(entity().uname, " ", entity().lname, " = ", entity().uname, "Mapper.toEntity(request", mapperBusinessRelationArguments(), ");");
        f.L________(entity().uname, " saved = ", entity().lname, "Repository.saveAndFlush(", entity().lname, ");");
        f.L________("return ", entity().uname, "Mapper.toResponse(saved);");
        f.L____("}");
    }

}
