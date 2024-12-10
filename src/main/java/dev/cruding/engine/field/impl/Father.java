package dev.cruding.engine.field.impl;

import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.flow.Flow;
import dev.cruding.engine.flow.JavaFlow;
import dev.cruding.engine.flow.JsFlow;
import dev.cruding.engine.gen.Context;

public class Father<T extends Entity> extends RefField<T> {

    public Father(Class<T> t) {
        super(t, false);
        isFather = true;
    }

    public void addJsDeclaration(JsFlow f) {
        f.addJsDeclaration("id" + uname, "string");
    }

    public void addJavaImport(JavaFlow f) {
        super.addJavaImport(f);
        Entity re = Context.getInstance().getEntity(jtype);
        f.addJavaImport("app.domain." + re.pkg + "." + re.lname + "." + re.uname + "Ref");
        f.addJavaImport("jakarta.persistence.ManyToOne");
        f.addJavaImport("jakarta.persistence.JoinColumn");
    }

    public void addJavaDeclaration(JavaFlow f) {
        f.L("");
        f.L____("@ManyToOne");
        f.L____("@JoinColumn(name = \"", jcDbName, "\")");
        f.L____("private " + jtype + "Ref " + lname + ";");


    }

    public void addGetterSetter(Flow f) {
        f.L("");
        f.L____("public " + jtype + "Ref get" + uname + "() {");
        f.L________("return this." + lname + ";");
        f.L____("}");
        f.L("");
        f.L____("public void set" + uname + "(" + jtype + "Ref " + lname + ") {");
        f.L________("this." + lname + " = " + lname + ";");
        f.L____("}");
    }

    protected Father<T> initCopy() {
        return new Father<T>(type);
    }
}
