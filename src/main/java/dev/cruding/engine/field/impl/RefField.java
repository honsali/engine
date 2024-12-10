package dev.cruding.engine.field.impl;

import org.apache.commons.lang3.StringUtils;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.field.Field;
import dev.cruding.engine.flow.Flow;
import dev.cruding.engine.flow.JsFlow;
import dev.cruding.engine.flow.MCFlow;
import dev.cruding.engine.flow.ViewFlow;
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
        lname(lname).jtype(type.getSimpleName());
    }

    public RefField(Class<T> type, boolean many) {
        this(type, many, StringUtils.uncapitalize(type.getSimpleName()));
    }

    public RefField<?> lname(String lname) {
        this.lname = lname;
        this.uname = StringUtils.capitalize(lname);
        this.dbTypeName = StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(type.getSimpleName()), "_").toLowerCase();
        this.dbName = StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(lname), "_").toLowerCase() + "_id";
        if (containingEntity != null && (isRef || isRefMany || isFather)) {
            this.jtDbName = Context.getInstance().getLegacyDbName(containingEntity, lname, "joinTable", containingEntityDbname + "_" + this.dbTypeName);
            this.jcDbName = Context.getInstance().getLegacyDbName(containingEntity, lname, "joinColumn", dbName);
            this.ijcDbName = Context.getInstance().getLegacyDbName(containingEntity, lname, "inverseJoinColumn", this.dbTypeName);
        }
        return this;
    }

    public void addJsImport(JsFlow f, Entity entity) {
        if (this.referencedEntity != null && !isFather && !entity.uname.equals(this.referencedEntity.uname)) {
            f.addJsImport("{ I" + this.referencedEntity.uname + " }", "modele/" + this.referencedEntity.path + "/Domaine" + this.referencedEntity.uname);
        }
    }

    public Field containingEntity(Entity entity) {
        this.referencedEntity = (T) Context.getInstance().getEntity(jtype);
        jstype("I" + this.referencedEntity.uname + (isRefMany ? "[]" : ""));

        this.containingEntity = entity.uname;
        this.containingEntityDbname = entity.dbName;

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

    public void addCtrlImport(MCFlow f) {
        if (init == null) {
            Entity entity = Context.getInstance().getEntity(jtype);
            f.addCtrlImport("Service" + entity.uname, "modele/" + entity.path + "/Service" + entity.uname);
        }
    }

    public void addCtrlImplementation(MCFlow f) {
        if (init == null) {
            Entity entity = Context.getInstance().getEntity(jtype);
            f.L____("resultat.liste", jtype, " = await Service", jtype, ".lister(");
            if (entity.haveFather) {
                f.__("requete.id" + entity.ufather);
            }
            f.__(");");
        }
    }

    public void addViewScript(ViewFlow f, String uc, String mvcPath) {
        if (initFromMdl) {
            f.addSpecificSelector("liste" + jtype, mvcPath + "/Mdl" + uc);
        }
    }

    public void addMdlImport(MCFlow f) {
        if (init == null) {
            f.addMdlImport("{ IReference }", "modele/commun/reference/DomaineReference");
        }
    }

    public void addMdlResultAttribute(MCFlow f) {
        if (init == null) {
            f.addMdlResultAttribute("liste" + jtype, "IReference[]");
        }
    }

    public void addMdlStateAttribute(MCFlow f) {
        if (init == null) {
            f.addMdlStateAttribute("liste" + jtype, "IReference[]");
        }
    }

    public void addMdlSelector(MCFlow f, String uc) {
        if (init == null) {
            f.L("export const selectListe", jtype, " = createSelector([selectMdl", uc, "], (state: ", uc, "Type) => state.liste", jtype, ");");
        }
    }

    public void addMdlExtraReducer(MCFlow f) {
        if (init == null) {
            f.L________________("state.liste", jtype, " = action.payload.liste", jtype, ";");
        }
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
