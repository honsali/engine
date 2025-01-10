package dev.cruding.engine.printer.impl.entite;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.entite.Entite;
import dev.cruding.engine.flow.JavaFlow;
import dev.cruding.engine.gen.Contexte;
import dev.cruding.engine.printer.Printer;

public class BeResourcePrinter extends Printer {

    public void print(Entite entite) {
        JavaFlow f = new JavaFlow();

        /* *********************************************************************** */


        if (entite.havePere) {
            Entite fe = Contexte.getInstance().getEntite(entite.pere.jtype);
            f.addJavaImport("app.domain." + fe.pkg + "." + fe.lname + "." + fe.uname + "Repository");
            f.addJavaImport("app.domain." + fe.pkg + "." + fe.lname + "." + fe.uname);
        }
        for (Action action : Contexte.getInstance().actionEntite(entite)) {
            action.resourceActionInjection.addResourceImport(f);
        }
        f.addJavaImport("java.util.List");
        f.addJavaImport("org.springframework.transaction.annotation.Transactional");
        f.addJavaImport("org.springframework.web.bind.annotation.GetMapping");
        f.addJavaImport("org.springframework.web.bind.annotation.RequestMapping");
        f.addJavaImport("org.springframework.web.bind.annotation.RestController");

        /* *********************************************************************** */
        f.__("package app.domain.", entite.pkg, ".", entite.lname, ";");
        f.L("");
        f.flushJavaImportBloc();
        f.L("");

        f.L("@RestController");
        f.L("@RequestMapping(\"/api/", entite.lname, "\")");
        f.L("@Transactional");
        f.L("public class ", entite.uname, "Resource {");
        f.L("");
        f.L____("private final ", entite.uname, "Repository ", entite.lname, "Repository;");
        if (entite.havePere) {
            f.L____("private final ", entite.upere, "Repository ", entite.lpere, "Repository;");
        }
        f.L("");
        f.L____("public ", entite.uname, "Resource(", entite.uname, "Repository ", entite.lname, "Repository");
        if (entite.havePere) {
            f.__(", ", entite.upere, "Repository ", entite.lpere, "Repository");
        }
        f.__(") {");
        f.L________("this.", entite.lname, "Repository = ", entite.lname, "Repository;");
        if (entite.havePere) {

            f.L________("this.", entite.lpere, "Repository = ", entite.lpere, "Repository;");
        }
        f.L____("}");


        for (Action action : Contexte.getInstance().actionEntite(entite)) {
            action.resourceActionInjection.addResourceDeclaration(f);
        }
        f.L("}");

        /* *********************************************************************** */
        String s = f.toString();
        printFile(s, getBasePath() + "/be/src/main/java/app/domain/" + entite.path + "/" + entite.uname + "Resource.java");
    }

}
