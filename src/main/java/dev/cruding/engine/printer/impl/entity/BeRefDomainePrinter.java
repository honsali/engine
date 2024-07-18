package dev.cruding.engine.printer.impl.entity;

import java.util.List;
import java.util.stream.Collectors;

import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.field.Field;
import dev.cruding.engine.flow.JavaFlow;
import dev.cruding.engine.printer.Printer;

public class BeRefDomainePrinter extends Printer {

    public void print(Entity entity) {
        JavaFlow f = new JavaFlow();

        /* *********************************************************************** */
        List<Field> isIdFieldList = entity.fieldList.stream().filter(p -> p.isId).toList();
        f.addJavaImport("jakarta.persistence.Id");
        for (Field fld : isIdFieldList) {
            fld.addJavaImport(f);
        }

        f.addJavaImport("jakarta.persistence.Column");
        f.addJavaImport("jakarta.persistence.Entity");
        f.addJavaImport("jakarta.persistence.Table");
        f.addJavaImport("java.io.Serializable");
        f.addJavaImport("org.hibernate.annotations.Cache");
        f.addJavaImport("org.hibernate.annotations.CacheConcurrencyStrategy");

        /* *********************************************************************** */

        f.__("package app.domain.", entity.module, ".", entity.lname, ";");
        f.L("");
        f.flushJavaImportBloc();
        f.L("");
        f.L("@Entity");
        f.L("@Table(name = \"", entity.dbName, "\")");
        f.L("@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)");
        f.L("public class ", entity.uname, "Ref implements Serializable {");
        f.L("");
        f.L____("private static final long serialVersionUID = 1L;");
        f.L("");
        entity.id_.addJavaDeclaration(f, entity.uname);
        for (Field fld : isIdFieldList) {
            fld.addJavaDeclaration(f);
        }
        f.L("");

        entity.id_.addGetterSetter(f, entity.uname + "Ref");
        if (!entity.isReferenceData()) {
            f.L("");
            f.L____("public String getDisplayString() {");
            f.L________("return ", isIdFieldList.stream().map(fld -> fld.lname).collect(Collectors.joining(" + ", "", "")), ";");
            f.L____("}");
        }
        for (Field fld : isIdFieldList) {
            fld.addGetterSetter(f);
        }
        f.L("");
        f.L____("@Override");
        f.L____("public boolean equals(Object o) {");
        f.L________("if (this == o) {");
        f.L____________("return true;");
        f.L________("}");
        f.L________("if (!(o instanceof ", entity.uname, "Ref)) {");
        f.L____________("return false;");
        f.L________("}");
        f.L________("return id != null && id.equals(((", entity.uname, "Ref) o).id);");
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
        printFile(s, getBasePath() + "/be/src/main/java/app/domain/" + entity.modulePath + '/' + entity.lname + "/" + entity.uname + "Ref.java");
    }

}