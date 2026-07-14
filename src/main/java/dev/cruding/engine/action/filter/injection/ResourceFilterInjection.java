package dev.cruding.engine.action.filter.injection;

import dev.cruding.engine.flow.JavaFlow;
import dev.cruding.engine.injection.ActionResourceInjection;

public class ResourceFilterInjection extends ActionResourceInjection {

    public void addResourceImport(JavaFlow f) {
        f.addJavaImport("org.springframework.data.domain.Pageable");
        f.addJavaImport("app.core.PageResponse");
        f.addJavaImport("org.springframework.web.bind.annotation.PostMapping");
        f.addJavaImport("org.springframework.web.bind.annotation.RequestBody");
    }

    public void addResourceDeclaration(JavaFlow f) {
        f.L("");
        f.L____("@PostMapping(\"/api/", entity().lname, "/", lnameWithoutEntity(), "\")");
        f.L____("public PageResponse<", entity().uname, "Dto> ", lnameWithoutEntity(), "(@Valid @RequestBody(required = false) ", entity().uname, "Filtre filtre, Pageable pageable) {");
        f.L________("return PageResponse.from(", entity().lname, "Service.filtrer(filtre, pageable));");
        f.L____("}");
    }
}
