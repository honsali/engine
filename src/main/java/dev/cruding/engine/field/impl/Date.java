package dev.cruding.engine.field.impl;

import dev.cruding.engine.element.Element;
import dev.cruding.engine.field.Field;
import dev.cruding.engine.flow.JavaFlow;
import dev.cruding.engine.flow.JsFlow;

public class Date extends Field {

    public Date(String lname) {
        super(true);
        lname(lname).jtype("LocalDate").jstype("string").stype("date");
    }

    public void addJavaImport(JavaFlow f, boolean addGlobal) {
        super.addJavaImport(f, addGlobal);
        f.addJavaImport("java.time.LocalDate");
    }

    public void addDtoImport(JavaFlow flow) {
        flow.addJavaImport("java.time.LocalDate");
    }

    public String ui(String element) {
        switch (element) {
            case Element.FORM:
                return "ChampDate";
            case Element.DETAIL:
                return "Texte";
            case Element.TABLE:
                return "Colonne tc=\"date\"";
            default:
                return "";
        }
    }

    public void addJsDeclaration(JsFlow f) {
        super.addJsDeclaration(f);
        if (filtrable) {
            f.L____("debut" + uname, "?: string;");
            f.L____("fin" + uname, "?: string;");
        }
    }

    public void addFilterJavaDeclaration(JavaFlow f) {
        f.L________("LocalDate debut" + uname + ",");
        f.L________("LocalDate fin" + uname);
    }


    public void addFilterGetterSetter(JavaFlow f) {
        f.L("");
        f.L____("public " + jtype + " getDebut" + uname + "() {");
        f.L________("return this.debut" + uname + ";");
        f.L____("}");
        f.L("");
        f.L____("public void setDebut" + uname + "(" + jtype + " debut" + uname + ") {");
        f.L________("this.debut" + uname + " = debut" + uname + ";");
        f.L____("}");
        f.L("");
        f.L____("public " + jtype + " getFin" + uname + "() {");
        f.L________("return this.fin" + uname + ";");
        f.L____("}");
        f.L("");
        f.L____("public void setFin" + uname + "(" + jtype + " fin" + uname + ") {");
        f.L________("this.fin" + uname + " = fin" + uname + ";");
        f.L____("}");
    }


    public void addSpecification(JavaFlow f) {
        f.L____________("addDateRange(predicates, criteriaBuilder, root.get(\"" + lname + "\"), condition.debut" + uname + "(), condition.fin" + uname + "());");
    }

    protected Field initCopy() {
        return new Date(lname);
    }
}
