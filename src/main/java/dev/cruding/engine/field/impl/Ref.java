package dev.cruding.engine.field.impl;

import org.apache.commons.lang3.StringUtils;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entity.Entity;
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
            f.addJavaImport("app.domain." + re.module + "." + re.lname + "." + re.uname);
        }
        if (!tranzient) {
            f.addJavaImport("jakarta.persistence.ManyToOne");
            f.addJavaImport("jakarta.persistence.JoinColumn");
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
            f.L____("@ManyToOne");
            f.L____("@JoinColumn(name = \"", jcDbName, "\")");
            if (!"\"\"".equals(s)) {
                f.L____("@JsonIgnoreProperties(value = {" + s + "}, allowSetters = true)");
            }
        }
        f.L____("private " + jtype + " " + lname + ";");

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
                return "reference";
            case Element.TABLEAU:
                return "Colonne tc=\"reference\"";
            default:
                return "";
        }

    }

    protected Ref<T> initCopy() {
        return new Ref<T>(type);
    }
}
