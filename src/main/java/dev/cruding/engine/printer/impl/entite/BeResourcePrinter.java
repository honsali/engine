package dev.cruding.engine.printer.impl.entite;

import java.util.HashSet;
import java.util.List;
import dev.cruding.engine.action.Action;
import dev.cruding.engine.entite.Entite;
import dev.cruding.engine.flow.JavaFlow;
import dev.cruding.engine.gen.Contexte;
import dev.cruding.engine.printer.Printer;

public class BeResourcePrinter extends Printer {

    public void print(Entite entite) {
        JavaFlow f = new JavaFlow();

        /* *********************************************************************** */

        for (Action action : Contexte.getInstance().actionEntite(entite)) {
            action.resourceActionInjection.addResourceImport(f);
        }
        f.addJavaImport("org.springframework.web.bind.annotation.GetMapping");
        f.addJavaImport("org.springframework.web.bind.annotation.RestController");

        /* *********************************************************************** */
        f.__("package app.domain.", entite.pkg, ".", entite.lname, ";");
        f.L("");
        f.flushJavaImportBloc();
        f.L("");
        f.L("@RestController");
        f.L("public class ", entite.uname, "Resource {");
        f.L("");
        f.L____("private final ", entite.uname, "Service ", entite.lname, "Service;");
        f.L("");
        f.L____("public ", entite.uname, "Resource(", entite.uname, "Service ", entite.lname, "Service) {");
        f.L________("this.", entite.lname, "Service = ", entite.lname, "Service;");
        f.L____("}");

        List<Action> actionList = Contexte.getInstance().actionEntite(entite);
        HashSet<String> actionName = new HashSet<>();
        for (Action action : actionList) {
            if (!actionName.contains(action.lnameSansEntite)) {
                action.resourceActionInjection.addResourceDeclaration(f);
                actionName.add(action.lnameSansEntite);
            }
        }


        f.L("}");

        /* *********************************************************************** */
        String s = f.toString();
        printFile(s, getBasePath() + "/be/src/main/java/app/domain/" + entite.path + "/" + entite.uname + "Resource.java");
    }

}
