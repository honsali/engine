package dev.cruding.engine.field.impl;

import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.flow.Flow;
import dev.cruding.engine.flow.JavaFlow;
import dev.cruding.engine.gen.Context;

public class RefMany<T extends Entity> extends RefField<T> {

    public RefMany(Class<T> t) {
        super(t, true);
    }

    public RefMany(Class<T> t, String lname) {
        super(t, true, lname);
        isRef = true;
    }

    public void addJavaImport(JavaFlow f) {
        super.addJavaImport(f);
        if (!jtype.equals(containingEntity)) {
            Entity re = Context.getInstance().getEntity(jtype);
            f.addJavaImport("app.domain." + re.pkg + "." + re.lname + "." + re.uname);
        }
        if (!tranzient) {
            f.addJavaImport("jakarta.persistence.OneToMany");
            f.addJavaImport("org.hibernate.annotations.Fetch");
            f.addJavaImport("org.hibernate.annotations.FetchMode");
            f.addJavaImport("java.util.HashSet");
            f.addJavaImport("java.util.Set");
        }
    }

    public void addJavaDeclaration(JavaFlow f) {
        String n = getReferenceName(jtype, containingEntity);
        f.L("");
        if (tranzient) {
            f.L____("@Transient");
        } else {
            f.L____("@OneToMany(mappedBy = \"", n, "\")");
            f.L____("@Fetch(FetchMode.JOIN)");
            f.L____("@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)");
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

    protected RefMany<T> initCopy() {
        return new RefMany<T>(type);
    }
}
