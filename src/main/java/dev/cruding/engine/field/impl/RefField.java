package dev.cruding.engine.field.impl;

import org.apache.commons.lang3.StringUtils;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.field.Field;
import dev.cruding.engine.flow.Flow;
import dev.cruding.engine.flow.JsFlow;
import dev.cruding.engine.gen.Context;

public class RefField<T extends Entity> extends Field {

    protected Class<T> type;
    public T referencedEntity;
    public String dbTypeName;
    public String jtDbName;
    public String jcDbName;
    public String ijcDbName;

    public RefField(Class<T> type, boolean many, String lname) {
        super(false);
        this.type = type;
        isRefMany = many;
        lname(lname).jtype(type.getSimpleName()).stype(dbName);
    }

    public RefField(Class<T> type, boolean many) {
        this(type, many, StringUtils.uncapitalize(type.getSimpleName()));
    }

    public RefField<?> lname(String lname) {
        this.lname = lname;
        this.uname = StringUtils.capitalize(lname);
        this.dbTypeName = StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(type.getSimpleName()), "_").toLowerCase();
        this.dbName = StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(lname), "_").toLowerCase() + "_id";
        return this;
    }

    public void addJsImport(JsFlow f) {
        if (this.referencedEntity != null && !isFather) {
            f.addJsImport("{ I" + this.referencedEntity.uname + " }", "modele/" + this.referencedEntity.modulePath + "/Domaine" + this.referencedEntity.uname);
        }
    }

    public Field containingEntity(Entity entity) {
        this.referencedEntity = (T) Context.getInstance().getEntity(jtype);
        jstype("I" + this.referencedEntity.uname + (isRefMany ? "[]" : ""));

        this.containingEntity = entity.uname;

        if (isRef || isRefMany || isFather) {
            this.jtDbName = Context.getInstance().getLegacyDbName(entity.uname, lname, "joinTable", entity.dbName + "_" + this.dbTypeName);
            this.jcDbName = Context.getInstance().getLegacyDbName(entity.uname, lname, "joinColumn", dbName);
            this.ijcDbName = Context.getInstance().getLegacyDbName(entity.uname, lname, "inverseJoinColumn", this.dbTypeName);
        }
        return this;
    }

    public void addLiqDeclaration(Flow f) {
        f.L____________("<column name=\"" + dbName + "\" type=\"bigint\">");
        f.L________________("<constraints nullable=\"" + !required + "\" />");
        f.L____________("</column>");
    }

    public RefField<T> dbName(String dbName) {
        RefField<T> p = makeCopy();
        p.dbName = dbName;
        p.dbTypeName = dbName.endsWith("_id") ? dbName.substring(0, -3) : dbName;
        return p;
    }

    protected RefField<T> initCopy() {
        return new RefField<T>(type, isRefMany);
    }

    protected RefField<T> makeCopy() {
        RefField<T> p = initCopy();
        p.jtDbName = this.jtDbName;
        p.jcDbName = this.jcDbName;
        p.ijcDbName = this.ijcDbName;
        p.dbTypeName = this.dbTypeName;
        return copyFieldProps(this, p);
    }

}
