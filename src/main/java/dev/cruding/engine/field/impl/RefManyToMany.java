package dev.cruding.engine.field.impl;

import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.flow.Flow;
import dev.cruding.engine.flow.JavaFlow;
import dev.cruding.engine.gen.Context;

public class RefManyToMany<T extends Entity> extends RefField<T> {

    public RefManyToMany(Class<T> t) {
        super(t, true);
    }

    public void addJavaImport(JavaFlow f) {
        super.addJavaImport(f);
        if (!jtype.equals(containingEntity)) {
            Entity re = Context.getInstance().getEntity(jtype);
            f.addJavaImport("app.domain." + re.module + "." + re.lname + "." + re.uname);
        }
        if (!tranzient) {
            f.addJavaImport("jakarta.persistence.ManyToMany");
            f.addJavaImport("jakarta.persistence.JoinTable");
            f.addJavaImport("jakarta.persistence.JoinColumn");
            f.addJavaImport("org.hibernate.annotations.Fetch");
            f.addJavaImport("org.hibernate.annotations.FetchMode");
            f.addJavaImport("java.util.HashSet");
            f.addJavaImport("java.util.Set");
            String s = getReferenceNameList(jtype);
            if (!"\"\"".equals(s)) {
                f.addJavaImport("com.fasterxml.jackson.annotation.JsonIgnoreProperties");
            }
        }
    }

    public void addJavaDeclaration(JavaFlow f) {

        String s = getReferenceNameList(jtype);
        f.L("");
        if (tranzient) {
            f.L____("@Transient");
        } else {
            f.L____("@ManyToMany");
            f.L____("@Fetch(FetchMode.JOIN)");
            f.L____("@JoinTable(name = \"", jtDbName, "\", joinColumns = @JoinColumn(name = \"", jcDbName, "\"), inverseJoinColumns = @JoinColumn(name = \"", ijcDbName, "\"))");
            f.L____("@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)");
            f.L____("@JsonIgnoreProperties(value = {" + s + "}, allowSetters = true)");
        }
        f.L____("private Set<" + jtype + "> " + lname + " = new HashSet<>();");
    }

    public void addGetterSetter(Flow f) {
        f.L("");
        f.L____("public Set<", jtype, "> get", uname, "() {");
        f.L________("return this.", lname, ";");
        f.L____("}");
        f.L("");
        f.L____("public void set", uname, "(Set<", jtype, "> ", lname, ") {");
        f.L________("this.", lname, " = ", lname, ";");
        f.L____("}");
    }

    public void addLiqDeclaration(Flow f) {
        f.L____________("<column name=\"", dbName, "_id\" type=\"bigint\">");
        f.L________________("<constraints nullable=\"", "" + !required, "\" />");
        f.L____________("</column>");
    }

    protected RefManyToMany<T> initCopy() {
        return new RefManyToMany<T>(type);
    }

}
