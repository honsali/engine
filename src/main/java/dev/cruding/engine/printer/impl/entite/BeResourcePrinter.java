package dev.cruding.engine.printer.impl.entite;

import java.util.List;
import dev.cruding.engine.action.Action;
import dev.cruding.engine.champ.Champ;
import dev.cruding.engine.entite.Entite;
import dev.cruding.engine.flow.JavaFlow;
import dev.cruding.engine.gen.Context;
import dev.cruding.engine.printer.Printer;

public class BeResourcePrinter extends Printer {

    public void print(Entite entite) {
        JavaFlow f = new JavaFlow();

        /* *********************************************************************** */

        List<Champ> childList = entite.fieldList.stream().filter(fld -> fld.isChild).toList();
        for (Champ fld : childList) {
            Entite re = Context.getInstance().getEntite(fld.jtype);
            f.addJavaImport("app.domain." + re.pkg + "." + re.lname + "." + re.uname + "Repository");
        }
        if (entite.haveFather) {
            Entite fe = Context.getInstance().getEntite(entite.father.jtype);
            f.addJavaImport("app.domain." + fe.pkg + "." + fe.lname + "." + fe.uname + "Repository");
            f.addJavaImport("app.domain." + fe.pkg + "." + fe.lname + "." + fe.uname);
        }
        for (Action action : Context.getInstance().actionEntite(entite)) {
            action.addResourceImport(f);
        }
        f.addJavaImport("java.util.List");
        f.addJavaImport("org.springframework.transaction.annotation.Transactional");
        f.addJavaImport("org.springframework.web.bind.annotation.GetMapping");
        f.addJavaImport("org.springframework.web.bind.annotation.RequestMapping");
        f.addJavaImport("org.springframework.web.bind.annotation.RestController");



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
        if (entite.haveFather) {
            f.L____("private final ", entite.ufather, "Repository ", entite.lfather, "Repository;");
        }
        for (Champ fld : childList) {
            // @TODO
            // f.L____("private final ", fld.uname, "RefRepository ", fld.lname, "RefRepository;");
        }
        f.L("");
        f.L____("public ", entite.uname, "Resource(", entite.uname, "Repository ", entite.lname, "Repository");

        if (entite.haveFather) {
            f.__(", ", entite.ufather, "Repository ", entite.lfather, "Repository");
        }   
        for (Champ fld : childList) {
            // @TODO
            // f.__(", ", fld.uname, "RefRepository ", fld.lname, "RefRepository");
        }
        f.__(") {");
        f.L________("this.", entite.lname, "Repository = ", entite.lname, "Repository;");
        if (entite.haveFather) {

            f.L________("this.", entite.lfather, "Repository = ", entite.lfather, "Repository;");
        }
        for (Champ fld : childList) {
            // @TODO
            // f.L________("this.", fld.lname, "RefRepository = ", fld.lname, "RefRepository;");
        }
        f.L____("}");


        for (Action action : Context.getInstance().actionEntite(entite)) {
            action.addResourceDeclaration(f);
        }
        f.L("}");

        /* *********************************************************************** */
        String s = f.toString();
        printFile(s, getBasePath() + "/be/src/main/java/app/domain/" + entite.path + "/" + entite.uname + "Resource.java");
    }

}
