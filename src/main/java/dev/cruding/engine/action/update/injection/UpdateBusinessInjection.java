package dev.cruding.engine.action.update.injection;

import dev.cruding.engine.action.specifique.injection.BasicBusinessInjection;
import dev.cruding.engine.field.Field;
import dev.cruding.engine.flow.JavaFlow;

public class UpdateBusinessInjection extends BasicBusinessInjection {


    public void addBusinessImport(JavaFlow f) {
        f.addJavaImport("java.util.NoSuchElementException");

        for (Field field : entity().fieldList) {
            if (field.isId) {
                f.addJavaImport("app.core.ConflictException");
            }
        }
    }

    public void addBusinessDeclaration(JavaFlow f) {
        f.L("");
        f.L____("public ", entity().uname, "Dto maj(Long id, ", entity().uname, "Dto dto) {");
        f.L________(entity().uname, " ", entity().lname, " = ", entity().lname, "Repository.findById(id).orElseThrow(() -> new NoSuchElementException(\"", entity().uname, " not found\"));");
        for (Field field : entity().fieldList) {
            if (field.isId) {
                f.L________("if (", entity().lname, "Repository.existsBy", field.uname, "AndIdNot(dto.", field.lname, "(), id)) {");
                f.L____________("throw new ConflictException(\"", field.uname, " already exists\");");
                f.L________("}");
            }
        }
        f.L________(entity().lname, "Mapper.copyToEntity(dto, ", entity().lname, ");");
        f.L________("return ", entity().lname, "Mapper.toDto(", entity().lname, ");");
        f.L____("}");



    }

}
