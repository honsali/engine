package dev.cruding.engine.printer.impl.entity;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.flow.JavaFlow;
import dev.cruding.engine.gen.Context;
import dev.cruding.engine.printer.Printer;

public class BeRepositoryPrinter extends Printer {

    public void print(Entity entity) {
        JavaFlow f = new JavaFlow();
        /* *********************************************************************** */
        if (entity.haveFather) {
            f.addJavaImport("java.util.List");
        }

        f.addJavaImport("org.springframework.data.jpa.repository.JpaRepository");
        f.addJavaImport("org.springframework.stereotype.Repository");

        for (Action action : Context.getInstance().actionEntity(entity)) {
            action.addRepositoryImport(f);
        }

        /* *********************************************************************** */

        f.__("package app.domain.", entity.module, ".", entity.lname, ";");

        f.L("");
        f.flushJavaImportBloc();

        f.L("");
        f.L("@Repository");
        f.L("public interface ", entity.uname, "Repository extends JpaRepository<", entity.uname, ", ", entity.id_.jtype, "> {");

        if (entity.haveFather) {
            f.L("");
            // @TODO f.L____("void deleteBy", entity.ufather, "Id(", father.id_.jtype, " id", entity.ufather,
            // ");");
        }

        for (Action action : Context.getInstance().actionEntity(entity)) {
            action.addRepositoryDeclaration(f);
        }
        f.L("}");

        /* *********************************************************************** */
        String s = f.toString();
        printFile(s, getBasePath() + "/be/src/main/java/app/domain/" + entity.modulePath + '/' + entity.lname + "/" + entity.uname + "Repository.java");
    }

}
