package dev.cruding.engine.printer.impl.entity;

import java.util.List;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.field.Field;
import dev.cruding.engine.field.impl.RefField;
import dev.cruding.engine.flow.JavaFlow;
import dev.cruding.engine.gen.Context;
import dev.cruding.engine.printer.Printer;

public class BeDomainPrinter extends Printer {

    public BeDomainPrinter(Context context) {
        super(context);
    }

    public void print(Entity entity) {
        JavaFlow f = new JavaFlow();
        List<Field> fields = entity.fieldList;

        /* *********************************************************************** */

        f.__("package app.domain.", entity.javaPackage(), ";");

        /* *********************************************************************** */

        f.addJavaImport("app.core.persistence.BaseEntity");
        f.addJavaImport("jakarta.persistence.Entity");
        f.addJavaImport("jakarta.persistence.Table");
        for (Field field : fields) {
            if ((field.isRef || field.isFather) && !field.jtype.equals(field.containingEntity)) {
                Entity re = context().getEntity(field.jtype);
                f.addJavaImport("app.domain." + re.javaPackage() + "." + re.uname);
            }
            if (field.tranzient) {
                f.addJavaImport("jakarta.persistence.Transient");
            } else if (field.isFather || field.isRef) {
                f.addJavaImport("jakarta.persistence.ManyToOne");
                f.addJavaImport("jakarta.persistence.JoinColumn");
                f.addJavaImport("jakarta.persistence.FetchType");
            } else if (field.isDate) {
                f.addJavaImport("java.time.LocalDate");
            }

        }
        f.L("");
        f.flushJavaImportBlock();

        /* *********************************************************************** */
        f.L("");
        f.L("@Entity");
        f.L("@Table(name = \"", entity.dbName, "\")");
        f.L("public class ", entity.uname, " extends BaseEntity {");

        /* *********************************************************************** */

        f.L("");
        for (Field field : fields) {
            if (field.tranzient) {
                f.L____("@Transient");
            } else if (field.isRef || field.isFather) {
                f.L____("@ManyToOne(fetch = FetchType.LAZY", field.required ? ", optional = false)" : ")");
                f.L____("@JoinColumn(name = \"", ((RefField<?>) field).jcDbName, "\"", field.required ? ", nullable = false)" : ")");
            }
            f.L____("private " + field.jtype + " " + field.lname + ";");
        }

        /* *********************************************************************** */

        f.L("");
        f.L____("protected ", entity.uname, "() {}");

        /* *********************************************************************** */

        f.L("");
        f.addMethodDeclaration(4, entity.uname + "(", parameterList(fields));
        for (Field field : fields) {
            f.L________("this.", field.lname, " = ", field.lname, ";");
        }
        f.L____("}");

        /* *********************************************************************** */

        for (Field field : fields) {
            f.L("");
            f.L____("public " + field.jtype + " get" + field.uname + "() {");
            f.L________("return " + field.lname + ";");
            f.L____("}");
        }

        /* *********************************************************************** */

        if (!entity.isReferenceData()) {
            List<Field> allFieldButFather = entity.listAllFieldButFather();
            f.L("");
            f.addMethodDeclaration(4, "public void update(", parameterList(allFieldButFather));
            for (Field field : allFieldButFather) {
                f.L________("this.", field.lname, " = ", field.lname, ";");
            }
            f.L____("}");
        }
        f.L("}");

        /* *********************************************************************** */
        String s = f.toString();
        printFile(s, getBasePath() + "/be/src/main/java/app/domain/" + entity.javaPath() + "/" + entity.uname + ".java");
    }

    private List<String> parameterList(List<Field> fields) {
        return fields.stream().map(field -> field.jtype + " " + field.lname).toList();
    }

}
