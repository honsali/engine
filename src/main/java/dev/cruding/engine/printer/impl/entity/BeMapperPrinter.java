package dev.cruding.engine.printer.impl.entity;

import java.util.HashSet;
import java.util.List;
import dev.cruding.engine.action.Action;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.field.Field;
import dev.cruding.engine.flow.JavaFlow;
import dev.cruding.engine.gen.Context;
import dev.cruding.engine.printer.Printer;

public class BeMapperPrinter extends Printer {

    public void print(Entity entity) {
        JavaFlow f = new JavaFlow();
        List<Field> fields = entity.fieldList;
        List<Action> actionList = Context.getInstance().actionEntity(entity);

        /* *********************************************************************** */

        f.__("package app.domain.", entity.javaPackage(), ";");

        /* *********************************************************************** */

        f.addJavaImport("app.core.reference.Reference");
        for (Action action : actionList) {
            action.mapperActionInjection.addMapperImport(f);
        }
        for (Field field : entity.listRefAndFather()) {
            Entity referenced = Context.getInstance().getEntity(field.jtype);
            if (!entity.uname.equals(referenced.uname)) {
                f.addJavaImport("app.domain." + referenced.javaPackage() + "." + referenced.uname);
                f.addJavaImport("app.domain." + referenced.javaPackage() + "." + referenced.uname + "Mapper");
            }
        }


        f.L("");
        f.flushJavaImportBlock();

        /* *********************************************************************** */

        f.L("");
        f.L("public final class ", entity.uname, "Mapper {");
        if (!entity.isReferenceData()) {


            /* *********************************************************************** */

            f.L("");
            f.L____("public static ", entity.uname, "Response toResponse(", entity.uname, " ", entity.lname, ") {");
            f.L________("return new ", entity.uname, "Response(");
            f.L________________(entity.lname, ".getId(),");
            for (Field field : fields) {
                if (field.isRef || field.isFather) {
                    f.L________________(entity.lname, ".get", field.uname, "() == null");
                    f.L________________________("? null");
                    f.L________________________(": ", field.jtype, "Mapper.toReference(", entity.lname, ".get", field.uname, "()),");
                } else {
                    f.L________________(entity.lname, ".get", field.uname, "(),");
                }
            }
            f.L________________(entity.lname, ".getVersion());");
            f.L____("}");

            /* *********************************************************************** */

            HashSet<String> actionName = new HashSet<>();
            for (Action action : actionList) {
                if (!actionName.contains(action.lnameWithoutEntity)) {
                    action.mapperActionInjection.addMapperDeclaration(f);
                    actionName.add(action.lnameWithoutEntity);
                }
            }
        }

        /* *********************************************************************** */

        f.L("");
        f.L____("public static Reference toReference(", entity.uname, " ", entity.lname, ") {");
        f.L________("return new Reference(", entity.lname, ".getId(), ", entity.lname, ".get", entity.uid, "());");
        f.L____("}");

        /* *********************************************************************** */

        f.L("");
        f.L____("private ", entity.uname, "Mapper() {}");

        /* *********************************************************************** */

        f.L("}");


        /* *********************************************************************** */

        String s = f.toString();
        printFile(s, getBasePath() + "/be/src/main/java/app/domain/" + entity.javaPath() + "/" + entity.uname + "Mapper.java");
    }

}
