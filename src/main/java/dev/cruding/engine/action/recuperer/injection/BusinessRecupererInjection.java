package dev.cruding.engine.action.recuperer.injection;

import dev.cruding.engine.flow.JavaFlow;
import dev.cruding.engine.injection.BusinessActionInjection;

public class BusinessRecupererInjection extends BusinessActionInjection {



    public void addBusinessImport(JavaFlow f) {
        f.addJavaImport("java.util.Optional");

    }

    public void addBusinessDeclaration(JavaFlow f) {
        f.L("");
        f.L____("@Transactional(readOnly = true)");
        f.L____("public Optional<", entite().uname, "Dto> ", lnameSansEntite(), "(", entite().id_.jtype, " ", parChamp()[0].lname, ") {");
        f.L________("return ", entite().lname, "Repository.findById(id).map(", entite().uname, "Dto::toDto);");
        f.L____("}");
    }

}
