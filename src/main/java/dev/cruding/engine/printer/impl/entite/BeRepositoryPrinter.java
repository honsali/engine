package dev.cruding.engine.printer.impl.entite;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.entite.Entite;
import dev.cruding.engine.flow.JavaFlow;
import dev.cruding.engine.gen.Contexte;
import dev.cruding.engine.printer.Printer;

public class BeRepositoryPrinter extends Printer {

    public void print(Entite entite) {
        JavaFlow f = new JavaFlow();
        /* *********************************************************************** */
        if (entite.havePere) {
            f.addJavaImport("java.util.List");
        }

        f.addJavaImport("org.springframework.data.jpa.repository.JpaRepository");
        f.addJavaImport("org.springframework.stereotype.Repository");

        for (Action action : Contexte.getInstance().actionEntite(entite)) {
            action.addRepositoryImport(f);
        }

        /* *********************************************************************** */

        f.__("package app.domain.", entite.pkg, ".", entite.lname, ";");

        f.L("");
        f.flushJavaImportBloc();

        f.L("");
        f.L("@Repository");
        f.L("public interface ", entite.uname, "Repository extends JpaRepository<", entite.uname, ", ", entite.id_.jtype, "> {");

        for (Action action : Contexte.getInstance().actionEntite(entite)) {
            action.addRepositoryDeclaration(f);
        }
        f.L("}");

        /* *********************************************************************** */
        String s = f.toString();
        printFile(s, getBasePath() + "/be/src/main/java/app/domain/" + entite.path + "/" + entite.uname + "Repository.java");
    }

}
