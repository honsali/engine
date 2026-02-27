package dev.cruding.engine.action.update.injection;

import dev.cruding.engine.action.specifique.injection.BasicBusinessInjection;
import dev.cruding.engine.flow.JavaFlow;

public class UpdateBusinessInjection extends BasicBusinessInjection {

    public void addResourceImport(JavaFlow f) {}

    public void addBusinessDeclaration(JavaFlow f) {
        f.L("");
        f.L____("public ", entity().uname, "Dto maj(Long id, ", entity().uname, "Dto dto) {");
        f.L________("if (!", entity().lname, "Repository.existsById(id)) {");
        f.L____________("throw new IllegalArgumentException(\"", entity().uname, " not found\");");
        f.L________("}");
        f.L________(entity().uname, " ", entity().lname, " = ", entity().uname, "Dto.toEntity(dto, id);");
        f.L________(entity().uname, " saved = ", entity().lname, "Repository.save(", entity().lname, ");");
        f.L________("return ", entity().uname, "Dto.toDto(saved);");
        f.L____("}");
    }

}
