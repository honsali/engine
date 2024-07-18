package dev.cruding.engine.field.impl;

import dev.cruding.engine.entity.Entity;
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
        f.addJavaImport("app.domain." + re.module + "." + re.lname + "." + re.uname);
        f.addJavaImport("jakarta.persistence.ManyToOne");
        f.addJavaImport("jakarta.persistence.JoinColumn");
        String s = getReferenceNameList(jtype);
        if (!"\"\"".equals(s)) {
            f.addJavaImport("com.fasterxml.jackson.annotation.JsonIgnoreProperties");
        }
    }

    public void addJavaDeclaration(JavaFlow f) {
        String s = getReferenceNameList(jtype);
        f.L("");
        f.L____("@ManyToOne");
        f.L____("@JoinColumn(name = \"", jcDbName, "\")");
        if (!"\"\"".equals(s)) {
            f.L____("@JsonIgnoreProperties(value = {" + s + "}, allowSetters = true)");
        }
        f.L____("private " + jtype + " " + lname + ";");

    }

    protected Father<T> initCopy() {
        return new Father<T>(type);
    }
}
