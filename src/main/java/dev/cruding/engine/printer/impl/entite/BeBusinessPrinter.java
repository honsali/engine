package dev.cruding.engine.printer.impl.entite;

import java.util.HashSet;
import java.util.List;
import dev.cruding.engine.action.Action;
import dev.cruding.engine.entite.Entite;
import dev.cruding.engine.flow.JavaFlow;
import dev.cruding.engine.gen.Contexte;
import dev.cruding.engine.printer.Printer;

public class BeBusinessPrinter extends Printer {

    public void print(Entite entite) {
        JavaFlow f = new JavaFlow();

        /* *********************************************************************** */



        for (Action action : Contexte.getInstance().actionEntite(entite)) {
            action.businessActionInjection.addBusinessImport(f);
        }
        f.addJavaImport("org.springframework.transaction.annotation.Transactional");
        f.addJavaImport("org.springframework.stereotype.Service");

        /* *********************************************************************** */
        f.__("package app.domain.", entite.pkg, ".", entite.lname, ";");
        f.L("");
        f.flushJavaImportBloc();
        f.L("");

        f.L("@Service");
        f.L("@Transactional");
        f.L("public class ", entite.uname, "Service {");
        f.L("");
        f.L____("private final ", entite.uname, "Repository ", entite.lname, "Repository;");
        if (entite.havePere) {
            f.L____("private final ", entite.upere, "Repository ", entite.lpere, "Repository;");
        }
        f.L("");
        f.L____("public ", entite.uname, "Service(", entite.uname, "Repository ", entite.lname, "Repository");
        if (entite.havePere) {
            f.__(", ", entite.upere, "Repository ", entite.lpere, "Repository");
        }
        f.__(") {");
        f.L________("this.", entite.lname, "Repository = ", entite.lname, "Repository;");
        if (entite.havePere) {

            f.L________("this.", entite.lpere, "Repository = ", entite.lpere, "Repository;");
        }
        f.L____("}");

        List<Action> actionList = Contexte.getInstance().actionEntite(entite);
        HashSet<String> actionName = new HashSet<>();
        for (Action action : actionList) {
            if (!actionName.contains(action.lnameSansEntite)) {
                action.businessActionInjection.addBusinessDeclaration(f);
                actionName.add(action.lnameSansEntite);
            }
        }


        f.L("}");

        /* *********************************************************************** */
        String s = f.toString();
        printFile(s, getBasePath() + "/be/src/main/java/app/domain/" + entite.path + "/" + entite.uname + "Service.java");
    }

}
