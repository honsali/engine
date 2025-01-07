package dev.cruding.engine.champ.impl;

import org.apache.commons.lang3.StringUtils;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entite.Entite;
import dev.cruding.engine.flow.Flow;
import dev.cruding.engine.flow.JavaFlow;
import dev.cruding.engine.flow.JsFlow;
import dev.cruding.engine.gen.Contexte;

public class Ref<T extends Entite> extends ChampRef<T> {

    public Ref(Class<T> t) {
        super(t, false);
        isRef = true;
    }

    public Ref(Class<T> t, String lname) {
        super(t, false, lname);
        isRef = true;
    }

    public void addJsDeclaration(JsFlow f) {
        f.addJsDeclaration(lname, "I" + referencedEntite.uname);
    }

    public void addDtoImport(JavaFlow flow) {
        Entite re = Contexte.getInstance().getEntite(jtype);
        flow.addJavaImport("app.domain." + re.pkg + "." + re.lname + "." + re.uname + "Dto");
    }

    public void addJavaImport(JavaFlow f) {
        super.addJavaImport(f);
        if (!jtype.equals(containingEntite)) {
            Entite re = Contexte.getInstance().getEntite(jtype);

            f.addJavaImport("app.domain." + re.pkg + "." + re.lname + "." + re.uname);
        }
        if (!tranzient) {
            f.addJavaImport("jakarta.persistence.ManyToOne");
            // f.addJavaImport("jakarta.persistence.JoinColumn");
            f.addJavaImport("jakarta.persistence.FetchType");
        }
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

    public void addGetterSetter(Flow f) {
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
