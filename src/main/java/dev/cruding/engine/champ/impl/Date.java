package dev.cruding.engine.champ.impl;

import dev.cruding.engine.champ.Champ;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.flow.JavaFlow;

public class Date extends Champ {

    public Date(String lname) {
        super(true);
        lname(lname).jtype("LocalDate").jstype("string").stype("date");
    }

    public void addJavaImport(JavaFlow flow) {
        super.addJavaImport(flow);
        flow.addJavaImport("java.time.LocalDate");
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
            case Element.TABLEAU:
                return "Colonne tc=\"date\"";
            default:
                return "";
        }
    }

    public void addFiltreJavaDeclaration(JavaFlow f) {
        f.L____("private LocalDate debut" + uname + ";");
        f.L____("private LocalDate fin" + uname + ";");
    }


    public void addFiltreGetterSetter(JavaFlow f) {
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
        f.L("");
        f.L____________("if (condition.getDebut" + uname + "() != null) {");
        f.L________________("predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(\"" + lname + "\"), condition.getDebut" + uname + "()));");
        f.L____________("}");
        f.L("");
        f.L____________("if (condition.getFin" + uname + "() != null) {");
        f.L________________("predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(\"" + lname + "\"), condition.getFin" + uname + "()));");
        f.L____________("}");
    }

    protected Champ initCopy() {
        return new Date(lname);
    }
}
