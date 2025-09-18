package dev.cruding.engine.action.creer.injection;

import dev.cruding.engine.action.specifique.injection.BusinessSpecifiqueInjection;
import dev.cruding.engine.flow.JavaFlow;

public class BusinessCreerInjection extends BusinessSpecifiqueInjection {

    public void addResourceImport(JavaFlow f) {}

    public void addBusinessDeclaration(JavaFlow f) {
        f.L("");
        f.L____("public ", entite().uname, "Dto creer(", entite().uname, "Dto dto) {");
        f.L________(entite().uname, " ", entite().lname, " = ", entite().uname, "Dto.toEntity(dto);");
        f.L________(entite().uname, " saved = ", entite().lname, "Repository.save(", entite().lname, ");");
        f.L________("return ", entite().uname, "Dto.toDto(saved);");
        f.L____("}");
    }

}
