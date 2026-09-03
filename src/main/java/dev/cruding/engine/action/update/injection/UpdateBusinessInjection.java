package dev.cruding.engine.action.update.injection;

import java.util.List;
import dev.cruding.engine.action.specifique.injection.BasicBusinessInjection;
import dev.cruding.engine.field.Field;
import dev.cruding.engine.flow.JavaFlow;

public class UpdateBusinessInjection extends BasicBusinessInjection {

    public boolean requiresEntityResolver() {
        return true;
    }

    public List<Field> businessRelationFields() {
        return entity().listRef();
    }


    public void addBusinessImport(JavaFlow f) {
        f.addJavaImport("app.core.exception.StaleVersionException");

        for (Field field : entity().fieldList) {
            if (field.isId) {
                f.addJavaImport("app.core.exception.FieldConflictException");
            }
        }
    }

    public void addBusinessDeclaration(JavaFlow f) {
        f.L("");
        f.L____("@Transactional");
        f.L____("public ", entity().uname, "Response ", lnameWithoutEntity(), "(Long id, ", requestName(), " request) {");
        f.L________(entity().uname, " ", entity().lname, " = recuperer", entity().uname, "(id);");
        f.L________("if (", entity().lname, ".getVersion() != request.version()) {");
        f.L____________("throw new StaleVersionException(\"", entity().uname, "\", id);");
        f.L________("}");
        for (Field field : entity().fieldList) {
            if (field.isId) {
                f.L________("if (", entity().lname, "Repository.existsBy", field.uname, "AndIdNot(request.", field.lname, "(), id)) {");
                f.L____________("throw new FieldConflictException(\"", entity().uname, "\", \"", field.lname, "\", request.", field.lname, "());");
                f.L________("}");
            }
        }
        addBusinessRelationDeclarations(f);
        f.L________(entity().uname, "Mapper.toEntity(", entity().lname, ", request", mapperBusinessRelationArguments(), ");");
        f.L________(entity().lname, "Repository.flush();");
        f.L________("return ", entity().uname, "Mapper.toResponse(", entity().lname, ");");
        f.L____("}");
    }

}
