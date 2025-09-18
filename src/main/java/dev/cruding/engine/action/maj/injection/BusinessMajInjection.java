package dev.cruding.engine.action.maj.injection;

import dev.cruding.engine.action.specifique.injection.BusinessSpecifiqueInjection;
import dev.cruding.engine.flow.JavaFlow;

public class BusinessMajInjection extends BusinessSpecifiqueInjection {

    public void addResourceImport(JavaFlow f) {}

    public void addBusinessDeclaration(JavaFlow f) {
        f.L("");
        f.L____("public ", entite().uname, "Dto maj(Long id, ", entite().uname, "Dto dto) {");
        f.L________("if (!", entite().lname, "Repository.existsById(id)) {");
        f.L____________("throw new IllegalArgumentException(\"", entite().uname, " not found\");");
        f.L________("}");
        f.L________(entite().uname, " ", entite().lname, " = ", entite().uname, "Dto.toEntity(dto, id);");
        f.L________(entite().uname, " saved = ", entite().lname, "Repository.save(", entite().lname, ");");
        f.L________("return ", entite().uname, "Dto.toDto(saved);");
        f.L____("}");
    }

}
