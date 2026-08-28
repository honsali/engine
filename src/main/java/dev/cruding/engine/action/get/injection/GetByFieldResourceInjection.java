package dev.cruding.engine.action.get.injection;

import dev.cruding.engine.flow.JavaFlow;
import dev.cruding.engine.injection.ActionResourceInjection;

public class GetByFieldResourceInjection extends ActionResourceInjection {



    public void addResourceImport(JavaFlow f) {
        f.addJavaImport("org.springframework.web.bind.annotation.GetMapping");
        f.addJavaImport("org.springframework.web.bind.annotation.PathVariable");
    }

    public void addResourceDeclaration(JavaFlow f) {
        f.L("");
        f.L____("@GetMapping(\"/{", byField()[0].lname, "}\")");
        f.L____("public ", entity().uname, "Response ", lnameWithoutEntity(), "(@PathVariable ", byField()[0].jtype, " ", byField()[0].lname, ") {");
        f.L________("return ", entity().lname, "Service.", lnameWithoutEntity(), "(", byField()[0].lname, ");");
        f.L____("}");
    }

}
