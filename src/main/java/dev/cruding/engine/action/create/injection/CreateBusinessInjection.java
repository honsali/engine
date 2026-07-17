package dev.cruding.engine.action.create.injection;

import dev.cruding.engine.action.specifique.injection.BasicBusinessInjection;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.field.Field;
import dev.cruding.engine.flow.JavaFlow;
import dev.cruding.engine.gen.Context;

public class CreateBusinessInjection extends BasicBusinessInjection {

    public void addBusinessImport(JavaFlow f) {
        if (byFatherId() && entity().haveFather) {
            f.addJavaImport("java.util.NoSuchElementException");
            Entity father = Context.getInstance().getEntity(entity().ufather);
            f.addJavaImport("app.domain." + father.pkg + "." + father.lname + "." + father.uname);
        }
        for (Field field : entity().fieldList) {
            if (field.isId) {
                f.addJavaImport("app.core.exception.ConflictException");
            }
        }
    }

    public void addBusinessDeclaration(JavaFlow f) {
        f.L("");
        f.L____("public ", entity().uname, "Dto creer(");
        if (byFatherId() && entity().haveFather) {
            f.__("Long id", entity().ufather + ", ");
        }

        f.__(entity().uname, "Dto dto) {");
        for (Field field : entity().fieldList) {
            if (field.isId) {
                f.L________("if (", entity().lname, "Repository.existsBy", field.uname, "(dto.", field.lname, "())) {");
                f.L____________("throw new ConflictException(\"", field.uname, " already exists\");");
                f.L________("}");
            }
        }
        if (byFatherId() && entity().haveFather) {
            f.L________("", entity().ufather, " ", entity().lfather, " = ", entity().lfather, "Repository.findById(id", entity().ufather, ").orElseThrow(() -> new NoSuchElementException(\"", entity().ufather, " not found\"));");
            f.L________("", entity().uname, " ", entity().lname, " = ", entity().lname, "Mapper.toEntity(dto);");
            f.L________("", entity().lname, ".set", entity().ufather, "(", entity().lfather, ");");
        } else {
            f.L________(entity().uname, " ", entity().lname, " = ", entity().lname, "Mapper.toEntity(dto);");
        }
        f.L________(entity().uname, " saved = ", entity().lname, "Repository.save(", entity().lname, ");");
        f.L________("return ", entity().lname, "Mapper.toDto(saved);");
        f.L____("}");
    }

}
