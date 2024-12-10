package dev.cruding.engine.field;

import java.util.Optional;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import dev.cruding.engine.element.ElementPrinter;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.field.impl.Ref;
import dev.cruding.engine.flow.Flow;
import dev.cruding.engine.flow.JavaFlow;
import dev.cruding.engine.flow.JsFlow;
import dev.cruding.engine.gen.Context;

public class Field extends FieldInAction {

    public String larg;
    public String uarg;
    public String lname;
    public String uname;
    public String dbName;
    public String jtype;
    public String stype;
    public String jstype;
    public boolean required;

    public boolean isRef;
    public boolean isRefMany;
    public boolean isChild;
    public boolean isBasic;
    public boolean isFather;
    public boolean isId;

    public boolean cloned = false;

    public String libelle;
    public int width;
    public boolean readOnly;
    public String readOnlyIf;
    public String invisibleSi;
    public String videSi;
    public String siChange;
    public boolean avecVariable;
    public boolean seulDansLaLigne;
    public boolean surTouteLaLigne;
    public boolean tranzient;
    public String reference;
    public String oui;
    public String non;
    public String init;
    public boolean initFromMdl;

    public Ref<?> of;

    public String containingEntity;
    public String containingEntityDbname;

    public Field(Field f) {
        copyFieldProps(f, this);
    }

    public Field(boolean isBasic) {
        this.isBasic = isBasic;
    }

    public Field lname(String lname) {
        this.lname = lname;
        this.uname = StringUtils.capitalize(lname);
        this.dbName = StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(lname), "_").toLowerCase();
        return this;
    }

    public Field containingEntity(Entity entity) {
        this.containingEntity = entity.uname;
        this.containingEntityDbname = entity.dbName;
        this.dbName = Context.getInstance().getLegacyDbName(entity.uname, lname, "column", this.dbName);
        return this;
    }

    public Field stype(String stype) {
        this.stype = stype;
        return this;
    }

    public Field jtype(String jtype) {
        this.jtype = jtype;
        return this;
    }

    public Field jstype(String jstype) {
        this.jstype = jstype;
        return this;
    }

    public Field width(int width) {
        Field p = makeCopy();
        p.width = width;
        return p;
    }

    public Field libelle(String libelle) {
        Field p = makeCopy();
        p.libelle = libelle;
        return p;
    }

    public Field required() {
        Field p = makeCopy();
        p.required = true;
        return p;
    }

    public Field init(String init) {
        Field p = makeCopy();
        p.init = init;
        return p;
    }

    public Field initFromMdl() {
        Field p = makeCopy();
        p.initFromMdl = true;
        return p;
    }


    public Field reference(String reference) {
        Field p = makeCopy();
        p.reference = reference;
        return p;
    }

    public Field isId() {
        Field p = makeCopy();
        p.isId = true;
        p.required = true;
        return p;
    }

    public Field readOnly() {
        Field p = makeCopy();
        p.readOnly = true;
        return p;
    }

    public Field readOnlyIf(String readOnlyIf) {
        Field p = makeCopy();
        p.readOnlyIf = readOnlyIf;
        return p;
    }

    public Field invisibleSi(String invisibleSi) {
        Field p = makeCopy();
        p.invisibleSi = invisibleSi;
        return p;
    }

    public Field videSi(String videSi) {
        Field p = makeCopy();
        p.videSi = videSi;
        return p;
    }

    public Field siChange() {
        Field p = makeCopy();
        p.siChange = "";
        return p;
    }

    public Field siChange(String siChange) {
        Field p = makeCopy();
        p.siChange = siChange;
        return p;
    }

    public Field avecVariable() {
        Field p = makeCopy();
        p.avecVariable = true;
        return p;
    }

    public Field seulDansLaLigne() {
        Field p = makeCopy();
        p.seulDansLaLigne = true;
        return p;
    }

    public Field surTouteLaLigne() {
        Field p = makeCopy();
        p.surTouteLaLigne = true;
        return p;
    }

    public Field tranzient() {
        Field p = makeCopy();
        p.tranzient = true;
        return p;
    }

    public Field oui(String oui) {
        Field p = makeCopy();
        p.oui = oui;
        return p;
    }

    public Field non(String non) {
        Field p = makeCopy();
        p.non = non;
        return p;
    }

    public Field arg(String arg) {
        Field p = makeCopy();
        p.larg = arg;
        p.uarg = StringUtils.capitalize(larg);
        return p;
    }

    public String ui(String element) {
        switch (element) {
            case ElementPrinter.FORM:
                return "ChampTexte";
            case ElementPrinter.DETAIL:
                return "nom";
            case ElementPrinter.TABLEAU:
                return "Colonne";
            default:
                return "";
        }

    }

    public String getExtension() {
        return "";
    }

    public StringBuilder getInit() {
        return null;
    }

    public void addJsImport(JsFlow f) {

    }

    public void addJsImport(JsFlow f, Entity entity) {
        addJsImport(f);
    }

    public void addJsDeclaration(JsFlow f) {
        f.addJsDeclaration(lname, jstype);
    }

    public void addJavaImport(JavaFlow f) {
        if (required) {
            f.addJavaImport("jakarta.validation.constraints.NotNull");
        }
        if (tranzient) {
            f.addJavaImport("jakarta.persistence.Transient");
        }
    }

    public void addJavaDeclaration(JavaFlow f) {
        f.L("");
        if (required) {
            f.L____("@NotNull");
        }
        if (tranzient) {
            f.L____("@Transient");
        } else {
            f.L____("@Column(name = \"" + dbName + "\"");
            if (required) {
                f.__(", nullable = false");
            }
            f.__(")");
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

    public void addLiqDeclaration(Flow f) {
        f.L____________("<column name=\"" + dbName + "\" type=\"" + stype + "\">");
        f.L________________("<constraints nullable=\"" + !required + "\" />");
        f.L____________("</column>");
    }

    public String getReferenceNameList(String entityName) {
        Entity entity = Context.getInstance().getEntity(entityName);
        if (entity != null && entity.fieldList.size() > 0) {
            return entity.fieldList.stream().filter(p -> p.isRef || p.isRefMany).map(p -> p.lname).collect(Collectors.joining("\", \"", "\"", "\""));
        }
        return null;
    }

    public String getReferenceName(String entityName, String c) {
        Entity entity = Context.getInstance().getEntity(entityName);
        if (entity != null && entity.fieldList.size() > 0) {
            Optional<String> o = entity.fieldList.stream().filter(p -> p.isRef || p.isRefMany).filter(p -> p.jtype.equals(c)).map(p -> p.lname).findAny();
            if (o.isPresent()) {
                return o.get();
            }
        }
        return null;
    }

    protected Field initCopy() {
        return new Field(true);
    }

    protected Field makeCopy() {
        Field p = initCopy();
        return copyFieldProps(this, p);
    }

    protected <T extends Field> T copyFieldProps(Field from, T to) {

        to.larg = from.larg;
        to.uarg = from.uarg;

        to.lname = from.lname;
        to.uname = from.uname;
        to.dbName = from.dbName;
        to.jtype = from.jtype;
        to.stype = from.stype;
        to.jstype = from.jstype;

        to.required = from.required;
        to.isRef = from.isRef;
        to.isRefMany = from.isRefMany;
        to.isChild = from.isChild;
        to.isBasic = from.isBasic;
        to.isFather = from.isFather;
        to.isId = from.isId;

        to.libelle = from.libelle;
        to.width = from.width;
        to.readOnly = from.readOnly;
        to.readOnlyIf = from.readOnlyIf;
        to.invisibleSi = from.invisibleSi;
        to.videSi = from.videSi;
        to.siChange = from.siChange;
        to.avecVariable = from.avecVariable;
        to.seulDansLaLigne = from.seulDansLaLigne;
        to.surTouteLaLigne = from.surTouteLaLigne;

        to.tranzient = from.tranzient;
        to.reference = from.reference;
        to.oui = from.oui;
        to.non = from.non;
        to.init = from.init;
        to.initFromMdl = from.initFromMdl;
        to.of = from.of;

        to.containingEntity = from.containingEntity;
        to.containingEntityDbname = from.containingEntityDbname;

        to.cloned = true;

        return to;
    }

}
