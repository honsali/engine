package dev.cruding.engine.action.filter.injection;

import dev.cruding.engine.flow.JavaFlow;
import dev.cruding.engine.injection.ActionResourceInjection;

public class ResourceFilterInjection extends ActionResourceInjection {

    public void addResourceImport(JavaFlow f) {
        f.addJavaImport("jakarta.validation.Valid");
        f.addJavaImport("org.springframework.data.domain.Pageable");
        f.addJavaImport("app.core.pagination.PageResponse");
        f.addJavaImport("org.springframework.web.bind.annotation.PostMapping");
        f.addJavaImport("org.springframework.web.bind.annotation.RequestBody");
    }

    public void addResourceDeclaration(JavaFlow f) {
        f.L("");
        f.L____("@PostMapping(\"/", entity().apiCollectionName(), "/", lnameWithoutEntity(), "\")");
        f.L____("public PageResponse<", entity().uname, "Response> ", lnameWithoutEntity(), "(@Valid @RequestBody(required = false) ", requestName(), " filtre, Pageable pageable) {");
        f.L________("return PageResponse.from(", entity().lname, "Service.", lnameWithoutEntity(), "(filtre, pageable));");
        f.L____("}");
    }
}
