package dev.cruding.engine.action.filter.injection;

import dev.cruding.engine.flow.JavaFlow;
import dev.cruding.engine.injection.ActionBusinessInjection;

public class BusinessFilterInjection extends ActionBusinessInjection {

    public void addBusinessImport(JavaFlow f) {
        f.addJavaImport("org.springframework.data.domain.Page");
        f.addJavaImport("org.springframework.data.domain.Pageable");
        f.addJavaImport("app.core.pagination.PageableUtils");
    }

    public void addBusinessDeclaration(JavaFlow f) {
        f.L("");
        f.L____("@Transactional(readOnly = true)");
        f.L____("public Page<", entity().uname, "Dto> ", lnameWithoutEntity(), "(", entity().uname, "Filtre filtre, Pageable pageable) {");
        f.L________("return ", entity().lname, "Repository.findAll(", entity().uname, "Specification.buildSpecification(filtre), PageableUtils.avecTriStable(pageable)).map(", entity().lname, "Mapper::toDto);");
        f.L____("}");
    }
}
