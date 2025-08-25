package dev.cruding.engine.printer.impl.entite;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import dev.cruding.engine.champ.Champ;
import dev.cruding.engine.entite.Entite;
import dev.cruding.engine.flow.JavaFlow;
import dev.cruding.engine.printer.BePrinterException;
import dev.cruding.engine.printer.Printer;

public class BeDomainePrinter extends Printer {

    private static final Predicate<Champ> IS_BASIC_REF_OR_PERE = p -> p.isBasic || p.isRef || p.isPere;

    public void print(Entite entite) {
        JavaFlow f = new JavaFlow();

        /* *********************************************************************** */


        List<Champ> isIdChampList = entite.listeChamp.stream().filter(p -> p.isId).toList();
        if (isIdChampList.isEmpty()) {
            throw new BePrinterException(String.format("Entity '%s' has no ID fields. Cannot generate domain class.", entite.uname));
        }
        List<Champ> notManyList = entite.listeChamp.stream().filter(IS_BASIC_REF_OR_PERE).toList();

        for (Champ champ : isIdChampList) {
            champ.addJavaImport(f);
        }
        for (Champ champ : notManyList) {
            champ.addJavaImport(f);
        }

        entite.id_.addJavaImport(f);
        f.addJavaImport("jakarta.persistence.Id");
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
        if (entite.id_.enLecture) {
            f.L("@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)");
        } else {
            f.L("@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)");
        }
        f.L("public class ", entite.uname, " implements Serializable {");
        f.L("");
        f.L____("private static final long serialVersionUID = ", generateSerialVersionUID(entite), ";");
        f.L("");
        entite.id_.addJavaDeclaration(f, entite.uname, entite.seqName);
        for (Champ champ : notManyList) {
            champ.addJavaDeclaration(f);
        }
        f.L("");

        entite.id_.addGetterSetter(f, entite.uname);
        if (!entite.isReferenceData()) {
            f.L("");
            f.L____("public String getDisplayString() {");
            f.L________("return ", isIdChampList.stream().map(champ -> champ.lname).collect(Collectors.joining(" + \" \" + ", "", "")), ";");
            f.L____("}");
        }
        f.L("");
        f.L____("public Long getId", entite.uname, "() {");
        f.L________("return this.id;");
        f.L____("}");
        for (Champ champ : notManyList) {
            champ.addGetterSetter(f);
        }
        f.L("");
        f.L____("@Override");
        f.L____("public boolean equals(Object o) {");
        f.L________("if (this == o) {");
        f.L____________("return true;");
        f.L________("}");
        f.L________("if (!(o instanceof ", entite.uname, ")) {");
        f.L____________("return false;");
        f.L________("}");
        f.L________("return getId() != null && getId().equals(((", entite.uname, ") o).getId());");
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
        printFile(s, getBasePath() + "/be/src/main/java/app/domain/" + entite.path + '/' + entite.uname + ".java");
    }

    private String generateSerialVersionUID(Entite entite) {
        String fullName = entite.pkg + "." + entite.lname;
        return Math.abs(fullName.hashCode()) + "L";
    }
}
