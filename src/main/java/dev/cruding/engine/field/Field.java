package dev.cruding.engine.field;

import java.util.Optional;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import dev.cruding.engine.action.Action;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.field.impl.Ref;
import dev.cruding.engine.flow.Flow;
import dev.cruding.engine.flow.JavaFlow;
import dev.cruding.engine.flow.JsFlow;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.Context;
import dev.cruding.engine.gen.DbNameMapper;

public class Field {

    public String defaultValue = "null";
    public String larg;
    public String uarg;
    public String lname;
    public String uname;
    public String dbName;
    public String jtype;
    public String stype;
    public String jstype;
    public boolean required;
    public String requiredIf;

    public boolean isRef;
    public boolean isRefMany;
    public boolean isChild;
    public boolean isBasic;
    public boolean isFather;
    public boolean isId;
    public boolean isUnique;
    public boolean isText;

    public boolean cloned = false;
    public String maxLength;

    public String label;
    public int width;
    public String onChange;
    public Action onChangeAction;
    public boolean readOnly;
    public String readOnlyIf;
    public String hiddenIf;
    public String emptyIf;
    public boolean watched;
    public boolean aloneInRow;
    public boolean wholeRow;
    public boolean tranzient;
    public String reference;
    public String yesValue;
    public String noValue;
    public String init;
    public boolean initFromMdl;
    public boolean filtrable;

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
        this.dbName = DbNameMapper.getInstance().getLegacyDbName(entity.uname, lname, "column", this.dbName);
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

    public Field label(String label) {
        Field p = makeCopy();
        p.label = label;
        return p;
    }

    public Field required(boolean required) {
        Field p = makeCopy();
        p.required = required;
        return p;
    }

    public Field required() {
        Field p = makeCopy();
        p.required = true;
        return p;
    }

    public Field requiredIf(String requiredIf) {
        Field p = makeCopy();
        p.requiredIf = requiredIf;
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
        p.isUnique = true;
        return p;
    }


    public Field isUnique() {
        Field p = makeCopy();
        p.isUnique = true;
        return p;
    }

    public Field iText() {
        Field p = makeCopy();
        p.isText = true;
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

    public Field hiddenIf(String hiddenIf) {
        Field p = makeCopy();
        p.hiddenIf = hiddenIf;
        return p;
    }

    public Field emptyIf(String emptyIf) {
        Field p = makeCopy();
        p.emptyIf = emptyIf;
        return p;
    }

    public Field onChange() {
        Field p = makeCopy();
        p.onChange = this.lname;
        return p;
    }

    public Field onChange(String onChange) {
        Field p = makeCopy();
        p.onChange = onChange;
        return p;
    }

    public Field onChange(Action action) {
        Field p = makeCopy();
        p.onChange = "";
        p.onChangeAction = action;
        return p;
    }


    public Field defaultValue(String defaultValue) {
        Field p = makeCopy();
        p.defaultValue = defaultValue;
        return p;
    }

    public Field maxLength(String maxLength) {
        Field p = makeCopy();
        p.maxLength = maxLength;
        return p;
    }

    public Field watched() {
        Field p = makeCopy();
        p.watched = true;
        return p;
    }

    public Field aloneInRow() {
        Field p = makeCopy();
        p.aloneInRow = true;
        return p;
    }

    public Field wholeRow() {
        Field p = makeCopy();
        p.wholeRow = true;
        return p;
    }

    public Field tranzient() {
        Field p = makeCopy();
        p.tranzient = true;
        return p;
    }

    public Field yesValue(String yesValue) {
        Field p = makeCopy();
        p.yesValue = yesValue;
        return p;
    }

    public Field noValue(String noValue) {
        Field p = makeCopy();
        p.noValue = noValue;
        return p;
    }

    public Field arg(String arg) {
        Field p = makeCopy();
        p.larg = arg;
        p.uarg = StringUtils.capitalize(larg);
        return p;
    }


    public Field filtrable() {
        Field p = makeCopy();
        p.filtrable = true;
        return p;
    }

    public String ui(String element) {
        switch (element) {
            case Element.FORM:
                return "ChampTexte";
            case Element.DETAIL:
                return "Texte";
            case Element.TABLE:
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
        addJsDeclaration(f, lname, jstype);
    }

    public void addJsDeclaration(JsFlow f, String lname, String type) {
        f.L____(lname, "?: ", type, ";");
    }

    public void addFilterImport(JavaFlow f) {
        addJavaImport(f, false);
    }


    public void addJavaImport(JavaFlow f, boolean addGlobal) {
        if (addGlobal) {
            if (required && isText) {
                f.addJavaImport("jakarta.validation.constraints.NotBlank");
            } else if (required) {
                f.addJavaImport("jakarta.validation.constraints.NotNull");
            }
            if (tranzient) {
                f.addJavaImport("jakarta.persistence.Transient");
            }
        } else {

            if (maxLength != null) {
                f.addJavaImport("jakarta.validation.constraints.Size");
            }
        }
    }

    public void addDtoDeclaration(JavaFlow f) {
        f.L________(jtype + " " + lname + ", //");
    }

    public void addDtoImport(JavaFlow flow) {

    }

    public boolean addViewScript(ViewFlow f, String uc, String mvcPath) {
        return false;
    }

    public void addFilterJavaDeclaration(JavaFlow f) {
        f.L________("");
        if (maxLength != null) {
            f.__("@Size(max = ", maxLength, ") ");
        }
        f.__(jtype + " " + lname);
    }

    public void addJavaDeclaration(JavaFlow f) {
        f.L("");
        if (required && isText) {
            f.L____("@NotBlank ");
        } else if (required) {
            f.L____("@NotNull");
        }


        if (tranzient) {
            f.L____("@Transient");
        } else {
            f.L____("@Column(name = \"" + dbName + "\"");
            if (required) {
                f.__(", nullable = false");
            }
            if (isUnique) {
                f.__(", unique = true");
            }
            f.__(")");
        }
        f.L____("private " + jtype + " " + lname + ";");
    }

    public void addFilterGetterSetter(JavaFlow f) {
        addGetterSetter(f);
    }

    public void addSpecification(JavaFlow f) {
        f.L____________("addLike(predicates, criteriaBuilder, root.get(\"" + lname + "\"), condition." + lname + "());");
    }

    public void addGetterSetter(JavaFlow f) {

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
        f.L________________("<constraints nullable=\"" + !required + "\" ");
        if (isUnique) {
            f.__("unique=\"true\" uniqueConstraintName=\"ux_", containingEntityDbname, "_", dbName, "\" ");
        }
        f.__("/>");
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
        to.requiredIf = from.requiredIf;
        to.isRef = from.isRef;
        to.isRefMany = from.isRefMany;
        to.isChild = from.isChild;
        to.isBasic = from.isBasic;
        to.isFather = from.isFather;
        to.isId = from.isId;
        to.isUnique = from.isUnique;
        to.isText = from.isText;


        to.maxLength = from.maxLength;

        to.label = from.label;
        to.width = from.width;
        to.readOnly = from.readOnly;
        to.readOnlyIf = from.readOnlyIf;
        to.hiddenIf = from.hiddenIf;
        to.emptyIf = from.emptyIf;
        to.onChange = from.onChange;
        to.onChangeAction = from.onChangeAction;
        to.defaultValue = from.defaultValue;
        to.watched = from.watched;
        to.aloneInRow = from.aloneInRow;
        to.wholeRow = from.wholeRow;

        to.tranzient = from.tranzient;
        to.reference = from.reference;
        to.yesValue = from.yesValue;
        to.noValue = from.noValue;
        to.init = from.init;
        to.initFromMdl = from.initFromMdl;
        to.of = from.of;
        to.filtrable = from.filtrable;

        to.containingEntity = from.containingEntity;
        to.containingEntityDbname = from.containingEntityDbname;

        to.cloned = true;

        return to;
    }

}
