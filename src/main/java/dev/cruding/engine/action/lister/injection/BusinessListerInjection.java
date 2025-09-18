package dev.cruding.engine.action.lister.injection;

import dev.cruding.engine.entite.Entite;
import dev.cruding.engine.flow.JavaFlow;
import dev.cruding.engine.gen.Contexte;
import dev.cruding.engine.injection.BusinessActionInjection;

public class BusinessListerInjection extends BusinessActionInjection {

    public void addBusinessImport(JavaFlow f) {
        f.addJavaImport("java.util.List");
        f.addJavaImport("java.util.stream.Collectors");
        if (entite().havePere) {
            Entite fe = Contexte.getInstance().getEntite(entite().pere.jtype);
            f.addJavaImport("app.domain." + fe.pkg + "." + fe.lname + "." + fe.uname + "Repository");
        }
    }

    public void addBusinessDeclaration(JavaFlow f) {
        f.L("");
        f.L____("@Transactional(readOnly = true)");
        f.L____("public List<", entite().uname, "Dto> ", lnameSansEntite(), "(");

        if (parIdPere() && entite().havePere) {
            f.__("Long id", entite().upere);
        }
        f.__(") {");

        if (parIdPere() && entite().havePere) {
            f.L________("if (!employeRepository.existsById(id", entite().upere, ")) {");
            f.L____________("throw new IllegalArgumentException(\"", entite().upere, " not found\");");
            f.L________("}");
        }
        f.L________("return ", entite().lname, "Repository.findAllBy");

        if (parIdPere() && entite().havePere) {
            f.__(entite().upere, "_Id");
        }

        f.__("OrderBy", orderBy(), "(");

        if (parIdPere() && entite().havePere) {
            f.__("id", entite().upere);
        }
        f.__(").stream().map(", entite().uname, "Dto::toDto).collect(Collectors.toList());");
        f.L____("}");
    }
}
