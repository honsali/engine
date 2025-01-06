package dev.cruding.engine.printer.impl.entite;

import java.util.List;
import java.util.stream.Collectors;
import dev.cruding.engine.champ.Champ;
import dev.cruding.engine.entite.Entite;
import dev.cruding.engine.flow.JavaFlow;
import dev.cruding.engine.printer.Printer;

public class BeRefDomainePrinter extends Printer {

    public void print(Entite entite) {
        JavaFlow f = new JavaFlow();

        /* *********************************************************************** */
        List<Champ> isIdChampList = entite.fieldList.stream().filter(p -> p.isId).toList();
        f.addJavaImport("jakarta.persistence.Id");
        for (Champ fld : isIdChampList) {
            fld.addJavaImport(f);
        }

        f.addJavaImport("jakarta.persistence.Column");
        f.addJavaImport("jakarta.persistence.Entity");
        f.addJavaImport("jakarta.persistence.Table");
        f.addJavaImport("java.io.Serializable");
        f.addJavaImport("org.hibernate.annotations.Cache");
        f.addJavaImport("org.hibernate.annotations.CacheConcurrencyStrategy");

        /* *********************************************************************** */

        f.__("package app.domain.", entite.pkg, ".", entite.lname, ";");
        f.L("");
        f.flushJavaImportBloc();
        f.L("");
        f.L("@Entity");
        f.L("@Table(name = \"", entite.dbName, "\")");
        f.L("@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)");
        f.L("public class ", entite.uname, "Ref implements Serializable {");
        f.L("");
        f.L____("private static final long serialVersionUID = 1L;");
        f.L("");
        entite.id_.addJavaDeclaration(f, entite.uname);
        for (Champ fld : isIdChampList) {
            fld.addJavaDeclaration(f);
        }
        f.L("");

        entite.id_.addGetterSetter(f, entite.uname + "Ref");
        if (!entite.isReferenceData()) {
            f.L("");
            f.L____("public String getDisplayString() {");
            f.L________("return ", isIdChampList.stream().map(fld -> fld.lname).collect(Collectors.joining(" + \" \" + ", "", "")), ";");
            f.L____("}");
        }
        f.L("");
        f.L____("public Long getId", entite.uname, "() {");
        f.L________("return this.id;");
        f.L____("}");
        for (Champ fld : isIdChampList) {
            fld.addGetterSetter(f);
        }
        f.L("");
        f.L____("@Override");
        f.L____("public boolean equals(Object o) {");
        f.L________("if (this == o) {");
        f.L____________("return true;");
        f.L________("}");
        f.L________("if (!(o instanceof ", entite.uname, "Ref)) {");
        f.L____________("return false;");
        f.L________("}");
        f.L________("return getId() != null && getId().equals(((", entite.uname, "Ref) o).id);");
        f.L____("}");
        f.L("");
        f.L____("@Override");
        f.L____("public int hashCode() {");
        f.L________("return getClass().hashCode();");
        f.L____("}");
        f.L("");
        f.L("}");

        /* *********************************************************************** */
        String s = f.toString();
        printFile(s, getBasePath() + "/be/src/main/java/app/domain/" + entite.path + "/" + entite.uname + "Ref.java");
    }

}
