package dev.cruding.engine.field.impl;

import org.apache.commons.lang3.StringUtils;
import dev.cruding.engine.field.Field;
import dev.cruding.engine.flow.JavaFlow;
import dev.cruding.engine.gen.Context;

public class Setting extends Field {

    public boolean idLong = true;
    public boolean enLecture = false;
    public boolean idSequence = true;
    public boolean feminin = false;
    public boolean voyelle = false;

    public Setting() {
        super(true);
        jtype("Long").jstype("string").stype("bigint");
        lname("id");
    }

    public Setting init(String uname) {
        if (libelle == null) {
            libelle = StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(uname), " ");
        }
        voyelle = uname.startsWith("A") || uname.startsWith("E") || uname.startsWith("I") || uname.startsWith("O") || uname.startsWith("U");
        return this;
    }

    public Setting idLong() {
        idLong = true;
        jtype("Long").jstype("string").stype("bigint");
        return this;
    }

    public Setting idTexte() {
        idLong = false;
        jtype("String").jstype("string").stype("varchar");
        return this;
    }

    public Setting idSequence(boolean idSequence) {
        Setting s = makeCopy();
        s.idSequence = idSequence;
        return s;
    }

    public Setting enLecture() {
        Setting s = makeCopy();
        s.enLecture = true;
        return s;
    }

    public Setting feminin() {
        Setting s = makeCopy();
        s.feminin = true;
        return s;
    }

    public Setting voyelle() {
        Setting s = makeCopy();
        s.voyelle = true;
        return s;
    }

    public String getDbName(String entityUname) {
        return Context.getInstance().getLegacyDbName(entityUname, "id", "column", "id");
    }

    public void addJavaImport(JavaFlow f) {
        f.addJavaImport("jakarta.persistence.Id");
        if (idSequence) {
            f.addJavaImport("jakarta.persistence.GeneratedValue");
            f.addJavaImport("jakarta.persistence.GenerationType");
            f.addJavaImport("jakarta.persistence.SequenceGenerator");
        }
    }

    public void addJavaDeclaration(JavaFlow f, String entityUname, String seqName) {

        String newDbName = Context.getInstance().getLegacyDbName(entityUname, "id", "column", "id");
        String jtype = idLong ? "Long" : "String";
        f.L____("@Id");
        if (idSequence) {
            f.L____("@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = \"", seqName, "\")");
            f.L____("@SequenceGenerator(name = \"", seqName, "\", allocationSize = 1)");
            f.L____("@Column(name = \"", newDbName, "\")");
            f.L____("private ", jtype, " id;");
        } else {
            f.L____("@Column(name = \"", newDbName, "\", columnDefinition = \"uniqueidentifier\")");
            f.L____("private ", jtype, " id;");

        }

    }

    public void addJavaDeclaration(JavaFlow f, String entityUname) {
        String newDbName = Context.getInstance().getLegacyDbName(entityUname, "id", "column", "id");
        String jtype = idLong ? "Long" : "String";
        f.L____("@Id");
        f.L____("@Column(name = \"", newDbName, "\", columnDefinition = \"uniqueidentifier\")");
        f.L____("private ", jtype, " id;");

    }

    public void addGetterSetter(JavaFlow f, String entityUname) {
        String jtype = idLong ? "Long" : "String";

        f.L____("public ", jtype, " getId() {");
        f.L________("return this.id;");
        f.L____("}");
        f.L("");
        f.L____("public ", entityUname, " id(", jtype, " id) {");
        f.L________("this.setId(id);");
        f.L________("return this;");
        f.L____("}");
        f.L("");
        f.L____("public void setId(", jtype, " id) {");
        f.L________("this.id = id;");
        f.L____("}");
    }

    public String ce() {
        if (voyelle && !feminin) {
            return "cet";
        } else if (feminin) {
            return "cette";
        }
        return "ce";
    }


    protected Setting initCopy() {
        return new Setting();
    }

    protected Setting makeCopy() {
        Setting s = initCopy();
        s.idLong = this.idLong;
        s.enLecture = this.enLecture;
        s.idSequence = this.idSequence;
        s.feminin = this.feminin;
        s.voyelle = this.voyelle;
        return copyFieldProps(this, s);

    }

}
