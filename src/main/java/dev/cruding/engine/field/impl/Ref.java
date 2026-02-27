package dev.cruding.engine.field.impl;

import org.apache.commons.lang3.StringUtils;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.flow.JavaFlow;
import dev.cruding.engine.flow.JsFlow;
import dev.cruding.engine.gen.Context;

public class Ref<T extends Entity> extends RefField<T> {

    public Ref(Class<T> t) {
        super(t, false);
        isRef = true;
    }

    public Ref(Class<T> t, String lname) {
        super(t, false, lname);
        isRef = true;
    }

    public void addJsDeclaration(JsFlow f) {
        f.addJsDeclaration(lname, "I" + referencedEntity.uname);
    }

    public void addDtoImport(JavaFlow flow) {
        Entity re = Context.getInstance().getEntity(jtype);
        flow.addJavaImport("app.domain." + re.pkg + "." + re.lname + "." + re.uname + "Dto");
    }


    public void addFilterImport(JavaFlow f) {
        if (!jtype.equals(containingEntity)) {
            Entity re = Context.getInstance().getEntity(jtype);
            f.addJavaImport("app.domain." + re.pkg + "." + re.lname + "." + re.uname + "Dto");
        }
    }

    public void addJavaImport(JavaFlow f) {
        super.addJavaImport(f);
        if (!jtype.equals(containingEntity)) {
            Entity re = Context.getInstance().getEntity(jtype);

            f.addJavaImport("app.domain." + re.pkg + "." + re.lname + "." + re.uname);
        }
        if (!tranzient) {
            f.addJavaImport("jakarta.persistence.ManyToOne");
            // f.addJavaImport("jakarta.persistence.JoinColumn");
            f.addJavaImport("jakarta.persistence.FetchType");
        }
    }

    public void addFilterJavaDeclaration(JavaFlow f) {
        f.L____("private " + uname + "Dto " + lname + ";");
    }

    public void addJavaDeclaration(JavaFlow f) {
        f.L("");
        if (tranzient) {
            f.L____("@Transient");
        } else {
            f.L____("@ManyToOne(fetch = FetchType.LAZY)");
            // f.L____("@JoinColumn(name = \"", jcDbName, "\")");
        }
        f.L____("private " + jtype + " " + lname + ";");

    }

    public void addSpecification(JavaFlow f) {
        f.L("");
        f.L____________("if (condition.get" + uname + "() != null && condition.get" + uname + "().id() != null) {");
        f.L________________("predicates.add(criteriaBuilder.equal(root.get(\"" + lname + "\").get(\"id\"), condition.get" + uname + "().id()));");
        f.L____________("}");
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


    public String getExtension() {
        String s = "";
        if (reference != null) {
            s = " reference=\"" + reference + "\"";
        } else if (!uname.equals(jtype) && !jtype.equals("Reference")) {
            s = " reference=\"" + StringUtils.uncapitalize(jtype) + "\"";
        }
        if (larg != null && larg.startsWith("{")) {
            return s + " arg={" + larg + "}";
        } else if (larg != null) {
            return s + " arg=\"" + larg + "\"";
        }
        return s;
    }

    public String ui(String element) {
        switch (element) {
            case Element.FORM:
                return larg != null ? "ChampReferenceAvecFiltre" : "ChampReference";
            case Element.DETAIL:
                return "Reference";
            case Element.TABLE:
                return "Colonne tc=\"reference\"";
            default:
                return "";
        }
    }

    protected Ref<T> initCopy() {
        return new Ref<T>(type);
    }
}
