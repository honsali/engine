package dev.cruding.engine.printer.impl.entite;

import dev.cruding.engine.entite.Entite;
import dev.cruding.engine.flow.JavaFlow;
import dev.cruding.engine.printer.Printer;

public class BeRefRepositoryPrinter extends Printer {

    public void print(Entite entite) {
        JavaFlow f = new JavaFlow();
        /* *********************************************************************** */

        f.addJavaImport("java.util.List");
        f.addJavaImport("org.springframework.data.jpa.repository.JpaRepository");
        f.addJavaImport("org.springframework.stereotype.Repository");

        /* *********************************************************************** */

        f.__("package app.domain.", entite.pkg, ".", entite.lname, ";");

        f.L("");
        f.flushJavaImportBloc();

        f.L("");
        f.L("@Repository");
        f.L("public interface ", entite.uname, "RefRepository extends JpaRepository<", entite.uname, "Ref, Long> {");
        f.L____("List<", entite.uname, "Ref> findAllByOrderBy", entite.uid, "();");
        f.L("");
        f.L("}");

        /* *********************************************************************** */
        String s = f.toString();
        printFile(s, getBasePath() + "/be/src/main/java/app/domain/" + entite.path + "/" + entite.uname + "RefRepository.java");
    }

}
