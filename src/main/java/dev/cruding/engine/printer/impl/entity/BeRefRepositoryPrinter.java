package dev.cruding.engine.printer.impl.entity;

import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.flow.JavaFlow;
import dev.cruding.engine.printer.Printer;

public class BeRefRepositoryPrinter extends Printer {

    public void print(Entity entity) {
        JavaFlow f = new JavaFlow();
        /* *********************************************************************** */

        f.addJavaImport("java.util.List");
        f.addJavaImport("org.springframework.data.jpa.repository.JpaRepository");
        f.addJavaImport("org.springframework.stereotype.Repository");

        /* *********************************************************************** */

        f.__("package app.domain.", entity.module, ".", entity.lname, ";");

        f.L("");
        f.flushJavaImportBloc();

        f.L("");
        f.L("@Repository");
        f.L("public interface ", entity.uname, "RefRepository extends JpaRepository<", entity.uname, "Ref, Long> {");

        f.L("");
        f.L____("List<", entity.uname, "Ref> findAllByOrderBy", entity.uid, "();");
        f.L("");
        f.L("}");

        /* *********************************************************************** */
        String s = f.toString();
        printFile(s, getBasePath() + "/be/src/main/java/app/domain/" + entity.modulePath + '/' + entity.lname + "/" + entity.uname + "RefRepository.java");
    }

}