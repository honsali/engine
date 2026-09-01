package dev.cruding.engine.printer.impl.entity;

import java.util.List;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.field.Field;
import dev.cruding.engine.flow.JavaFlow;
import dev.cruding.engine.printer.Printer;

public class BeResponsePrinter extends Printer {

    public void print(Entity entity) {
        JavaFlow f = new JavaFlow();
        List<Field> fields = entity.fieldList;

        /* *********************************************************************** */

        f.__("package app.domain.", entity.javaPackage(), ";");

        /* *********************************************************************** */

        f.addJavaImport("app.core.reference.JsonId");
        for (Field field : fields) {
            if (field.isDate) {
                f.addJavaImport("java.time.LocalDate");
            } else if (field.isRef || field.isFather) {
                f.addJavaImport("app.core.reference.Reference");
            }
        }

        f.L("");
        f.flushJavaImportBlock();

        /* *********************************************************************** */


        f.L("");
        f.L("public record ", entity.uname, "Response(");
        f.L________("@JsonId Long id,");
        for (Field field : fields) {
            f.L________(field.isRef || field.isFather ? "Reference" : field.jtype, " ", field.lname, ",");
        }
        f.L________("long version) {");
        f.L("}");


        /* *********************************************************************** */

        String s = f.toString();
        printFile(s, getBasePath() + "/be/src/main/java/app/domain/" + entity.javaPath() + "/" + entity.uname + "Response.java");
    }



}
