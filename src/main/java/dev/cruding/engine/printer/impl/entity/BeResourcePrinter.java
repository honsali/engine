package dev.cruding.engine.printer.impl.entity;

import java.util.List;
import dev.cruding.engine.action.Action;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.field.Field;
import dev.cruding.engine.flow.JavaFlow;
import dev.cruding.engine.gen.Context;
import dev.cruding.engine.printer.Printer;

public class BeResourcePrinter extends Printer {

    public void print(Entity entity) {
        JavaFlow f = new JavaFlow();

        /* *********************************************************************** */

        List<Field> childList = entity.fieldList.stream().filter(fld -> fld.isChild).toList();
        for (Field fld : childList) {
            Entity re = Context.getInstance().getEntity(fld.jtype);
            f.addJavaImport("app.domain." + re.pkg + "." + re.lname + "." + re.uname + "Repository");
        }
        if (entity.haveFather) {
            Entity fe = Context.getInstance().getEntity(entity.father.jtype);
            f.addJavaImport("app.domain." + fe.pkg + "." + fe.lname + "." + fe.uname + "RefRepository");
            f.addJavaImport("app.domain." + fe.pkg + "." + fe.lname + "." + fe.uname + "Ref");
        }
        for (Action action : Context.getInstance().actionEntity(entity)) {
            action.addResourceImport(f);
        }
        f.addJavaImport("java.util.List");
        f.addJavaImport("org.springframework.transaction.annotation.Transactional");
        f.addJavaImport("org.springframework.web.bind.annotation.GetMapping");
        f.addJavaImport("org.springframework.web.bind.annotation.RequestMapping");
        f.addJavaImport("org.springframework.web.bind.annotation.RestController");



        f.__("package app.domain.", entity.pkg, ".", entity.lname, ";");
        f.L("");
        f.flushJavaImportBloc();
        f.L("");

        f.L("@RestController");
        f.L("@RequestMapping(\"/api/", entity.lname, "\")");
        f.L("@Transactional");
        f.L("public class ", entity.uname, "Resource {");
        f.L("");
        f.L____("private final ", entity.uname, "Repository ", entity.lname, "Repository;");
        f.L____("private final ", entity.uname, "RefRepository ", entity.lname, "RefRepository;");
        if (entity.haveFather) {
            f.L____("private final ", entity.ufather, "RefRepository ", entity.lfather, "RefRepository;");
        }
        for (Field fld : childList) {
            f.L____("private final ", fld.uname, "RefRepository ", fld.lname, "RefRepository;");
        }
        f.L("");
        f.L____("public ", entity.uname, "Resource(", entity.uname, "Repository ", entity.lname, "Repository, ", entity.uname, "RefRepository ", entity.lname, "RefRepository");

        if (entity.haveFather) {
            f.__(", ", entity.ufather, "RefRepository ", entity.lfather, "RefRepository");
        }
        for (Field fld : childList) {
            f.__(", ", fld.uname, "RefRepository ", fld.lname, "RefRepository");
        }
        f.__(") {");
        f.L________("this.", entity.lname, "Repository = ", entity.lname, "Repository;");
        f.L________("this.", entity.lname, "RefRepository = ", entity.lname, "RefRepository;");
        if (entity.haveFather) {
            f.L________("this.", entity.lfather, "RefRepository = ", entity.lfather, "RefRepository;");
        }
        for (Field fld : childList) {
            f.L________("this.", fld.lname, "RefRepository = ", fld.lname, "RefRepository;");
        }
        f.L____("}");

        f.L("");
        f.L____("@GetMapping(\"/listeReference\")");
        f.L____("public List<", entity.uname, "Ref> listerEnTantQueReference() {");
        f.L________("return ", entity.lname, "RefRepository.findAllByOrderBy", entity.uid, "();");
        f.L____("}");

        for (Action action : Context.getInstance().actionEntity(entity)) {
            action.addResourceDeclaration(f);
        }
        f.L("}");

        /* *********************************************************************** */
        String s = f.toString();
        printFile(s, getBasePath() + "/be/src/main/java/app/domain/" + entity.path + "/" + entity.uname + "Resource.java");
    }

}
