package dev.cruding.engine.field.impl;

import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.flow.Flow;
import dev.cruding.engine.flow.JavaFlow;
import dev.cruding.engine.flow.JsFlow;
import dev.cruding.engine.gen.Context;

public class Father<T extends Entity> extends RefField<T> {

    public Father(Class<T> t) {
        super(t, false, true);
    }

    public void addJsDeclaration(JsFlow f) {
        addJsDeclaration(f, lname, "I" + referencedEntity.uname);
    }

    public void addDtoImport(JavaFlow flow) {
        Entity re = Context.getInstance().getEntity(jtype);
        flow.addJavaImport("app.domain." + re.pkg + "." + re.lname + "." + re.uname + "Dto");
    }


    public void addFilterImport(JavaFlow f) {
        Entity re = Context.getInstance().getEntity(jtype);
        f.addJavaImport("app.domain." + re.pkg + "." + re.lname + "." + re.uname + "Dto");
    }

    public void addJavaImport(JavaFlow f, boolean addGlobal) {
        super.addJavaImport(f, addGlobal);
        Entity re = Context.getInstance().getEntity(jtype);
        f.addJavaImport("app.domain." + re.pkg + "." + re.lname + "." + re.uname);
        f.addJavaImport("jakarta.validation.constraints.NotNull");
        f.addJavaImport("jakarta.persistence.ManyToOne");
        // f.addJavaImport("jakarta.persistence.JoinColumn");
        f.addJavaImport("jakarta.persistence.FetchType");
    }


    public void addFilterJavaDeclaration(JavaFlow f) {
        f.L________(uname + "Dto " + lname);
    }

    public void addJavaDeclaration(JavaFlow f) {
        f.L("");
        f.L____("@NotNull");
        f.L____("@ManyToOne(fetch = FetchType.LAZY, optional = false)");
        f.L____("@JoinColumn(name = \"", jcDbName, "\", nullable = false)");
        f.L____("private " + jtype + " " + lname + ";");

    }


    public void addFilterGetterSetter(JavaFlow f) {
        f.L("");
        f.L____("public " + uname + "Dto get" + uname + "() {");
        f.L________("return this." + lname + ";");
        f.L____("}");
        f.L("");
        f.L____("public void set" + uname + "(" + uname + "Dto " + lname + ") {");
        f.L________("this." + lname + " = " + lname + ";");
        f.L____("}");
    }

    public void addGetterSetter(JavaFlow f) {
        f.L("");
        f.L____("public " + jtype + " get" + uname + "() {");
        f.L________("return this." + lname + ";");
        f.L____("}");
        f.L("");
        f.L____("public void set" + uname + "(" + jtype + " " + lname + ") {");
        f.L________("this." + lname + " = " + lname + ";");
        f.L____("}");
    }

    public void addLiqDeclaration(Flow f) {
        f.L____________("<column name=\"" + dbName + "\" type=\"bigint\">");
        f.L________________("<constraints nullable=\"false\" />");
        f.L____________("</column>");
    }

    public void addSpecification(JavaFlow f) {
        f.L____________("addEqual(predicates, criteriaBuilder, root.get(\"" + lname + "\").get(\"id\"), condition." + lname + "() == null ? null : condition." + lname + "().id());");
    }

    protected Father<T> initCopy() {
        return new Father<T>(type);
    }
}
