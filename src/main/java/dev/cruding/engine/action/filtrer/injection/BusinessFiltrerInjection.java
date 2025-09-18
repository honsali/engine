package dev.cruding.engine.action.filtrer.injection;

import dev.cruding.engine.flow.JavaFlow;
import dev.cruding.engine.injection.BusinessActionInjection;

public class BusinessFiltrerInjection extends BusinessActionInjection {

    public void addBusinessImport(JavaFlow f) {
        f.addJavaImport("org.springframework.data.domain.Page");
        f.addJavaImport("org.springframework.data.domain.Pageable");
    }

    public void addBusinessDeclaration(JavaFlow f) {
        f.L("");
        f.L____("@Transactional(readOnly = true)");
        f.L____("public Page<", entite().uname, "Dto> ", lnameSansEntite(), "(", entite().uname, "Filtre filtre, Pageable pageable) {");
        f.L________("return ", entite().lname, "Repository.findAll(", entite().uname, "Specification.buildSpecification(filtre), pageable).map(", entite().uname, "Dto::toDto);");
        f.L____("}");
    }
}
