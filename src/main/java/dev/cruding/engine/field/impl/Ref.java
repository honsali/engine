package dev.cruding.engine.field.impl;

import org.apache.commons.lang3.StringUtils;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.flow.JavaFlow;
import dev.cruding.engine.flow.JsFlow;

public class Ref<T extends Entity> extends RefField<T> {

    public Ref(Class<T> t) {
        super(t, false);
    }

    public Ref(Class<T> t, String lname) {
        super(t, false, lname);
    }

    public void addJsDeclaration(JsFlow f) {
        addJsDeclaration(f, lname, "IReference");
    }

    public void addFilterImport(JavaFlow f) {
        f.addJavaImport("jakarta.validation.Valid");
        f.addJavaImport("app.core.reference.Reference");
    }

    public void addFilterJavaDeclaration(JavaFlow f) {
        f.L________("@Valid Reference " + lname);
    }

    public void addSpecification(JavaFlow f) {
        f.L____________("addReference(predicates, builder, root, \"" + lname + "\", filtre." + lname + "());");
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
