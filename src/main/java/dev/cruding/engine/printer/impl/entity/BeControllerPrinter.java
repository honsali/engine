package dev.cruding.engine.printer.impl.entity;

import java.util.HashSet;
import java.util.List;
import dev.cruding.engine.action.Action;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.flow.JavaFlow;
import dev.cruding.engine.gen.Context;
import dev.cruding.engine.printer.Printer;

public class BeControllerPrinter extends Printer {

    public void print(Entity entity) {
        JavaFlow f = new JavaFlow();

        for (Action action : Context.getInstance().actionEntity(entity)) {
            action.resourceActionInjection.addResourceImport(f);
        }
        f.addJavaImport("org.springframework.web.bind.annotation.RequestMapping");
        f.addJavaImport("org.springframework.web.bind.annotation.RestController");

        f.__("package app.domain.", entity.pkg, ".", entity.lname, ";");
        f.L("");
        f.flushJavaImportBlock();
        f.L("");
        f.L("@RestController");
        f.L("@RequestMapping(\"/api", entity.apiDomainPath(), "/", entity.lname, "\")");
        f.L("public class ", entity.uname, "Controller {");
        f.L("");
        f.L____("private final ", entity.uname, "Service ", entity.lname, "Service;");
        f.L("");
        f.L____("public ", entity.uname, "Controller(", entity.uname, "Service ", entity.lname, "Service) {");
        f.L________("this.", entity.lname, "Service = ", entity.lname, "Service;");
        f.L____("}");

        List<Action> actionList = Context.getInstance().actionEntity(entity);
        HashSet<String> actionName = new HashSet<>();
        for (Action action : actionList) {
            if (!actionName.contains(action.lnameWithoutEntity)) {
                action.resourceActionInjection.addResourceDeclaration(f);
                actionName.add(action.lnameWithoutEntity);
            }
        }

        f.L("}");

        printFile(f.toString(), getBasePath() + "/be/src/main/java/app/domain/" + entity.path + "/" + entity.uname + "Controller.java");
    }
}
