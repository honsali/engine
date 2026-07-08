package dev.cruding.engine.printer.impl.entity;

import java.util.HashSet;
import java.util.List;
import dev.cruding.engine.action.Action;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.flow.JavaFlow;
import dev.cruding.engine.gen.Context;
import dev.cruding.engine.printer.Printer;

public class BeBusinessPrinter extends Printer {

    public void print(Entity entity) {
        JavaFlow f = new JavaFlow();

        /* *********************************************************************** */

        List<Action> actionList = Context.getInstance().actionEntity(entity);

        for (Action action : actionList) {
            action.businessActionInjection.addBusinessImport(f);
        }
        f.addJavaImport("org.springframework.transaction.annotation.Transactional");
        f.addJavaImport("org.springframework.stereotype.Service");

        /* *********************************************************************** */
        f.__("package app.domain.", entity.pkg, ".", entity.lname, ";");
        f.L("");
        f.flushJavaImportBlock();
        f.L("");

        f.L("@Service");
        f.L("@Transactional");
        f.L("public class ", entity.uname, "Service {");
        f.L("");
        f.L____("private final ", entity.uname, "Repository ", entity.lname, "Repository;");
        if (entity.haveFather) {
            f.L____("private final ", entity.ufather, "Repository ", entity.lfather, "Repository;");
        }
        f.L____("private final ", entity.uname, "Mapper ", entity.lname, "Mapper;");
        f.L("");
        f.L____("public ", entity.uname, "Service(", entity.uname, "Repository ", entity.lname, "Repository");
        if (entity.haveFather) {
            f.__(", ", entity.ufather, "Repository ", entity.lfather, "Repository");
        }
        f.__(", ", entity.uname, "Mapper ", entity.lname, "Mapper) {");
        f.L________("this.", entity.lname, "Repository = ", entity.lname, "Repository;");
        if (entity.haveFather) {

            f.L________("this.", entity.lfather, "Repository = ", entity.lfather, "Repository;");
        }
        f.L________("this.", entity.lname, "Mapper = ", entity.lname, "Mapper;");
        f.L____("}");

        HashSet<String> actionName = new HashSet<>();
        for (Action action : actionList) {
            if (!actionName.contains(action.lnameWithoutEntity)) {
                action.businessActionInjection.addBusinessDeclaration(f);
                actionName.add(action.lnameWithoutEntity);
            }
        }

        f.L("}");

        /* *********************************************************************** */
        String s = f.toString();
        printFile(s, getBasePath() + "/be/src/main/java/app/domain/" + entity.path + "/" + entity.uname + "Service.java");
    }

}
