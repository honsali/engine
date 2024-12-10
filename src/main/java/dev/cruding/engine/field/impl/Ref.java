package dev.cruding.engine.field.impl;

import org.apache.commons.lang3.StringUtils;
import dev.cruding.engine.element.ElementPrinter;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.flow.Flow;
import dev.cruding.engine.flow.JavaFlow;
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

    public void addJavaImport(JavaFlow f) {
        super.addJavaImport(f);
        if (!jtype.equals(containingEntity)) {
            Entity re = Context.getInstance().getEntity(jtype);

            f.addJavaImport("app.domain." + re.pkg + "." + re.lname + "." + re.uname + getRef());
        }
        if (!tranzient) {
            f.addJavaImport("jakarta.persistence.ManyToOne");
            f.addJavaImport("jakarta.persistence.JoinColumn");
        }
    }

    public void addJavaDeclaration(JavaFlow f) {
        f.L("");
        if (tranzient) {
            f.L____("@Transient");
        } else {
            f.L____("@ManyToOne");
            f.L____("@JoinColumn(name = \"", jcDbName, "\")");
        }
        f.L____("private " + jtype + getRef() + " " + lname + ";");

    }

    public void addGetterSetter(Flow f) {
        f.L("");
        f.L____("public " + jtype + getRef() + " get" + uname + "() {");
        f.L________("return this." + lname + ";");
        f.L____("}");
        f.L("");
        f.L____("public void set" + uname + "(" + jtype + getRef() + " " + lname + ") {");
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
            case ElementPrinter.FORM:
                return larg != null ? "ChampReferenceAvecFiltre" : "ChampReference";
            case ElementPrinter.DETAIL:
                return "reference";
            case ElementPrinter.TABLEAU:
                return "Colonne tc=\"reference\"";
            default:
                return "";
        }

    }

    protected Ref<T> initCopy() {
        return new Ref<T>(type);
    }

    private String getRef() {
        if (jtype.equals("Reference")) {
            return "";
        }
        return "Ref";
    }
}
