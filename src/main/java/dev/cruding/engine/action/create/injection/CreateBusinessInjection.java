package dev.cruding.engine.action.create.injection;

import dev.cruding.engine.action.specifique.injection.BasicBusinessInjection;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.flow.JavaFlow;
import dev.cruding.engine.gen.Context;

public class CreateBusinessInjection extends BasicBusinessInjection {

    public void addBusinessImport(JavaFlow f) {
        if (byFatherId() && entity().haveFather) {
            Entity father = Context.getInstance().getEntity(entity().ufather);
            f.addJavaImport("app.domain." + father.pkg + "." + father.lname + "." + father.uname);
        }
    }

    public void addBusinessDeclaration(JavaFlow f) {
        f.L("");
        f.L____("public ", entity().uname, "Dto creer(");
        if (byFatherId() && entity().haveFather) {
            f.__("Long id", entity().ufather + ", ");
        }

        f.__(entity().uname, "Dto dto) {");
        if (byFatherId() && entity().haveFather) {
            f.L________("if (dto != null && dto.", entity().lfather, "() != null && dto.", entity().lfather, "().id() != null && !dto.", entity().lfather, "().id().equals(id", entity().ufather, ")) {");
            f.L____________("throw new IllegalArgumentException(\"", entity().ufather, " ID mismatch\");");
            f.L________("}");
            f.L________("", entity().ufather, " ", entity().lfather, " = ", entity().lfather, "Repository.findById(id", entity().ufather, ").orElseThrow(() -> new java.util.NoSuchElementException(\"", entity().ufather, " not found\"));");
            f.L________("", entity().uname, " ", entity().lname, " = ", entity().uname, "Dto.toEntity(dto);");
            f.L________("", entity().lname, ".set", entity().ufather, "(", entity().lfather, ");");
        } else {
            f.L________(entity().uname, " ", entity().lname, " = ", entity().uname, "Dto.toEntity(dto);");
        }
        f.L________(entity().uname, " saved = ", entity().lname, "Repository.save(", entity().lname, ");");
        f.L________("return ", entity().uname, "Dto.toDto(saved);");
        f.L____("}");
    }

}
