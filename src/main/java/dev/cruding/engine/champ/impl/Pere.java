package dev.cruding.engine.champ.impl;

import dev.cruding.engine.entite.Entite;
import dev.cruding.engine.flow.Flow;
import dev.cruding.engine.flow.JavaFlow;
import dev.cruding.engine.flow.JsFlow;
import dev.cruding.engine.gen.Contexte;

public class Pere<T extends Entite> extends ChampRef<T> {

    public Pere(Class<T> t) {
        super(t, false);
        isPere = true;
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
        Entite re = Contexte.getInstance().getEntite(jtype);
        f.addJavaImport("app.domain." + re.pkg + "." + re.lname + "." + re.uname);
        f.addJavaImport("jakarta.persistence.ManyToOne");
        // f.addJavaImport("jakarta.persistence.JoinColumn");
        f.addJavaImport("jakarta.persistence.FetchType");
    }

    public void addJavaDeclaration(JavaFlow f) {
        f.L("");
        f.L____("@ManyToOne(fetch = FetchType.LAZY)");
        // f.L____("@JoinColumn(name = \"", jcDbName, "\")");
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

    protected Pere<T> initCopy() {
        return new Pere<T>(type);
    }
}
