package dev.cruding.engine.printer.impl.entity;

import java.util.List;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.field.Field;
import dev.cruding.engine.flow.JavaFlow;
import dev.cruding.engine.printer.Printer;

public class BeDomainePrinter extends Printer {

    public void print(Entity entity) {
        JavaFlow f = new JavaFlow();

        /* *********************************************************************** */
        List<Field> notManyList = entity.fieldList.stream().filter(p -> p.isBasic || p.isRef || p.isFather).toList();
        entity.id_.addJavaImport(f);
        for (Field fld : notManyList) {
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
        if (entity.id_.enLecture) {
            f.L("@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)");
        } else {
            f.L("@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)");
        }
        f.L("public class ", entity.uname, " implements Serializable {");
        f.L("");
        f.L____("private static final long serialVersionUID = 1L;");
        f.L("");
        entity.id_.addJavaDeclaration(f, entity.uname, entity.seqName);
        for (Field fld : notManyList) {
            fld.addJavaDeclaration(f);
        }
        f.L("");

        entity.id_.addGetterSetter(f, entity.uname);
        if (!entity.isReferenceData()) {
            f.L("");
            f.L____("public String getDisplayString() {");
            f.L________("return ", entity.lid, ";");
            f.L____("}");
        }
        f.L("");
        f.L____("public Long getId", entity.uname, "() {");
        f.L________("return this.id;");
        f.L____("}");
        for (Field fld : notManyList) {
            fld.addGetterSetter(f);
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
        f.L________("return id != null && id.equals(((", entity.uname, ") o).id);");
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
        printFile(s, getBasePath() + "/be/src/main/java/app/domain/" + entity.modulePath + '/' + entity.lname + "/" + entity.uname + ".java");
    }

}