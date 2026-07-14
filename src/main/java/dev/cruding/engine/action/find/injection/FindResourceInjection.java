package dev.cruding.engine.action.find.injection;

import dev.cruding.engine.flow.JavaFlow;
import dev.cruding.engine.injection.ActionResourceInjection;

public class FindResourceInjection extends ActionResourceInjection {

    public void addResourceImport(JavaFlow f) {
        f.addJavaImport("org.springframework.data.domain.Pageable");
        f.addJavaImport("app.core.PageResponse");
        f.addJavaImport("org.springframework.web.bind.annotation.PostMapping");
        f.addJavaImport("org.springframework.web.bind.annotation.RequestBody");
    }

    public void addResourceDeclaration(JavaFlow f) {
        f.L("");
        f.L____("@PostMapping(\"/", lnameWithoutEntity(), "\")");
        f.L____("public PageResponse<", entity().uname, "> ", lnameWithoutEntity(), "(@RequestBody ", entity().uname, " ", entity().lname, ", Pageable pageable) {");
        f.L________("return PageResponse.from(", entity().lname, "Repository.", lnameWithoutEntity(), "(", entity().lname, ", pageable));");
        f.L____("}");
    }
}
