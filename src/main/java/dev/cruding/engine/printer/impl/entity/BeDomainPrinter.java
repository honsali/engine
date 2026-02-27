package dev.cruding.engine.printer.impl.entity;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.field.Field;
import dev.cruding.engine.flow.JavaFlow;
import dev.cruding.engine.printer.BePrinterException;
import dev.cruding.engine.printer.Printer;

public class BeDomainPrinter extends Printer {

    private static final Predicate<Field> IS_BASIC_REF_OR_FATHER = p -> p.isBasic || p.isRef || p.isFather;

    public void print(Entity entity) {
        JavaFlow f = new JavaFlow();

        /* *********************************************************************** */


        List<Field> isIdFieldList = entity.fieldList.stream().filter(p -> p.isId).toList();
        if (isIdFieldList.isEmpty()) {
            throw new BePrinterException(String.format("Entity '%s' has no ID fields. Cannot generate domain class.", entity.uname));
        }
        List<Field> notManyList = entity.fieldList.stream().filter(IS_BASIC_REF_OR_FATHER).toList();

        for (Field field : isIdFieldList) {
            field.addJavaImport(f);
        }
        for (Field field : notManyList) {
            field.addJavaImport(f);
        }

        entity.id_.addJavaImport(f);
        f.addJavaImport("jakarta.persistence.Id");
        f.addJavaImport("jakarta.persistence.Column");
        f.addJavaImport("jakarta.persistence.Entity");
        f.addJavaImport("jakarta.persistence.Table");
        f.addJavaImport("java.io.Serializable");
        f.addJavaImport("org.hibernate.annotations.Cache");
        f.addJavaImport("org.hibernate.annotations.CacheConcurrencyStrategy");

        /* *********************************************************************** */

        f.__("package app.domain.", entity.pkg, ".", entity.lname, ";");
        f.L("");
        f.flushJavaImportBlock();
        f.L("");
        f.L("@Entity");
        f.L("@Table(name = \"", entity.dbName, "\")");
        if (entity.id_.readOnly) {
            f.L("@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)");
        } else {
            f.L("@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)");
        }
        f.L("public class ", entity.uname, " implements Serializable {");
        f.L("");
        f.L____("private static final long serialVersionUID = ", generateSerialVersionUID(entity), ";");
        f.L("");
        entity.id_.addJavaDeclaration(f, entity.uname, entity.seqName);
        for (Field field : notManyList) {
            field.addJavaDeclaration(f);
        }
        f.L("");

        entity.id_.addGetterSetter(f, entity.uname);
        if (!entity.isReferenceData()) {
            f.L("");
            f.L____("public String getDisplayString() {");
            f.L________("return ", isIdFieldList.stream().map(fld -> fld.lname).collect(Collectors.joining(" + \" \" + ", "", "")), ";");
            f.L____("}");
        }
        f.L("");
        f.L____("public Long getId", entity.uname, "() {");
        f.L________("return this.id;");
        f.L____("}");
        for (Field field : notManyList) {
            field.addGetterSetter(f);
        }
        f.L("");
        f.L____("@Override");
        f.L____("public boolean equals(Object o) {");
        f.L________("if (this == o) {");
        f.L____________("return true;");
        f.L________("}");
        f.L________("if (!(o instanceof ", entity.uname, ")) {");
        f.L____________("return false;");
        f.L________("}");
        f.L________("return getId() != null && getId().equals(((", entity.uname, ") o).getId());");
        f.L____("}");
        f.L("");
        f.L____("@Override");
        f.L____("public int hashCode() {");
        f.L________("return getClass().hashCode();");
        f.L____("}");
        f.L("");
        f.L("}");

        /* *********************************************************************** */
        String s = f.toString();
        printFile(s, getBasePath() + "/be/src/main/java/app/domain/" + entity.path + '/' + entity.uname + ".java");
    }

    private String generateSerialVersionUID(Entity entity) {
        String fullName = entity.pkg + "." + entity.lname;
        return Math.abs(fullName.hashCode()) + "L";
    }
}
