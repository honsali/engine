package dev.cruding.engine.field.impl;

import org.apache.commons.lang3.StringUtils;
import dev.cruding.engine.field.Field;
import dev.cruding.engine.flow.JavaFlow;
import dev.cruding.engine.gen.DbNameMapper;

public class Setting extends Field {

    public boolean idLong = true;
    public boolean readOnly = false;
    public boolean idSequence = true;
    public boolean feminine = false;
    public boolean vowel = false;

    public Setting() {
        super(true);
        jtype("Long").jstype("string").stype("bigint");
        lname("id");
    }

    public Setting init(String uname) {
        if (label == null) {
            label = StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(uname), " ");
        }
        vowel = uname.startsWith("A") || uname.startsWith("E") || uname.startsWith("I") || uname.startsWith("O") || uname.startsWith("U");

        return this;
    }

    public Setting idLong() {
        idLong = true;
        jtype("Long").jstype("string").stype("bigint");
        return this;
    }

    public Setting idText() {
        idLong = false;
        jtype("String").jstype("string").stype("varchar");
        return this;
    }

    public Setting idSequence(boolean idSequence) {
        Setting s = makeCopy();
        s.idSequence = idSequence;
        return s;
    }

    public Setting readOnly() {
        Setting s = makeCopy();
        s.readOnly = true;
        return s;
    }

    public Setting feminine() {
        Setting s = makeCopy();
        s.feminine = true;
        return s;
    }

    public Setting vowel() {
        Setting s = makeCopy();
        s.vowel = true;
        return s;
    }

    public String getDbName(String entityUname) {
        return DbNameMapper.getInstance().getLegacyDbName(entityUname, "id", "column", "id");
    }

    public void addJavaImport(JavaFlow f, boolean addGlobal) {
        f.addJavaImport("jakarta.persistence.Id");
        if (idSequence) {
            f.addJavaImport("jakarta.persistence.GeneratedValue");
            f.addJavaImport("jakarta.persistence.GenerationType");
            f.addJavaImport("jakarta.persistence.SequenceGenerator");
        }
    }

    public void addJavaDeclaration(JavaFlow f, String entityUname, String seqName) {

        String newDbName = DbNameMapper.getInstance().getLegacyDbName(entityUname, "id", "column", "id");
        String jtype = idLong ? "Long" : "String";
        f.L____("@Id");
        if (idSequence) {
            f.L____("@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = \"", seqName, "\")");
            f.L____("@SequenceGenerator(name = \"", seqName, "\", sequenceName = \"", seqName, "\", allocationSize = 1)");
            f.L____("@Column(name = \"", newDbName, "\")");
            f.L____("private ", jtype, " id;");
        } else {
            f.L____("@Column(name = \"", newDbName, "\", columnDefinition = \"uniqueidentifier\")");
            f.L____("private ", jtype, " id;");

        }

    }

    public void addJavaDeclaration(JavaFlow f, String entityUname) {
        String newDbName = DbNameMapper.getInstance().getLegacyDbName(entityUname, "id", "column", "id");
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
        f.L____("public void setId(", jtype, " id) {");
        f.L________("this.id = id;");
        f.L____("}");
    }

    public String that() {
        if (vowel && !feminine) {
            return "cet";
        } else if (feminine) {
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
        s.readOnly = this.readOnly;
        s.idSequence = this.idSequence;
        s.feminine = this.feminine;
        s.vowel = this.vowel;
        return copyFieldProps(this, s);

    }

}
