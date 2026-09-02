package dev.cruding.engine.field.impl;

import org.apache.commons.lang3.StringUtils;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.field.Field;
import dev.cruding.engine.flow.CtrlFlow;
import dev.cruding.engine.flow.Flow;
import dev.cruding.engine.flow.JsFlow;
import dev.cruding.engine.flow.MdlFlow;
import dev.cruding.engine.flow.ViewFlow;

public class RefField<T extends Entity> extends Field {

    protected Class<T> type;
    public T referencedEntity;
    public String dbTypeName;
    public String jtDbName;
    public String jcDbName;
    public String ijcDbName;

    public RefField(Class<T> type, boolean father, String lname) {
        super(false);
        this.type = type;
        isFather = father;
        isRef = !father;
        lname(lname).jtype(type.getSimpleName());
    }

    public RefField(Class<T> type, boolean father) {
        this(type, father, StringUtils.uncapitalize(type.getSimpleName()));
    }

    public RefField<?> lname(String lname) {
        this.lname = lname;
        this.uname = StringUtils.capitalize(lname);
        this.dbTypeName = StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(type.getSimpleName()), "_").toLowerCase();
        this.dbName = StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(lname), "_").toLowerCase() + "_id";
        if (containingEntity != null && (isRef || isFather)) {
            this.jtDbName = context().getDbNameMapper().getLegacyDbName(containingEntity, lname, "joinTable", containingEntityDbname + "_" + this.dbTypeName);
            this.jcDbName = context().getDbNameMapper().getLegacyDbName(containingEntity, lname, "joinColumn", dbName);
            this.ijcDbName = context().getDbNameMapper().getLegacyDbName(containingEntity, lname, "inverseJoinColumn", this.dbTypeName);
        }
        return this;
    }

    public void addJsImport(JsFlow f, Entity entity) {
        f.addJsImport("{ IReference }", "modele/commun/reference/DomaineReference");
    }

    public Field containingEntity(Entity entity) {
        this.context = entity.context();
        this.referencedEntity = (T) context().getEntity(jtype);
        jstype("IReference");

        this.containingEntity = entity.uname;
        this.containingEntityDbname = entity.dbName;

        return this;
    }

    public void addLiqDeclaration(Flow f) {
        f.L____________("<column name=\"" + dbName + "\" type=\"bigint\">");
        f.L________________("<constraints nullable=\"" + !required + "\" />");
        f.L____________("</column>");
    }

    public void addCtrlImport(CtrlFlow f) {
        if (init == null) {
            Entity entity = context().getEntity(jtype);
            f.addCtrlImport("Service" + entity.uname, "modele/" + entity.path + "/Service" + entity.uname);
        }
    }

    public void addCtrlImplementation(CtrlFlow f) {
        if (init == null) {
            Entity entity = context().getEntity(jtype);
            f.L____("resultat.liste", jtype, " = await Service", jtype);
            if (entity.haveFather) {
                f.__(".listerParId", entity.ufather, "(requete.id" + entity.ufather, ");");
            } else {
                f.__(".lister();");
            }

        }
    }

    public boolean addViewScript(ViewFlow f, String uc, String mvcPath) {
        if (initFromMdl) {
            f.addSelector("liste" + jtype);
            return true;
        }
        return false;
    }

    public void addMdlImport(MdlFlow f) {
        if (init == null) {
            f.addMdlImport("{ IReference }", "modele/commun/reference/DomaineReference");
        }
    }

    public void addMdlResultAttribute(MdlFlow f) {
        if (init == null) {
            f.addMdlResultAttribute("liste" + jtype, "IReference[]");
        }
    }

    public void addMdlStateAttribute(MdlFlow f) {
        if (init == null) {
            f.addMdlStateAttribute("liste" + jtype, "IReference[]");
            f.addMdlSelectorAttribute("liste" + jtype, "Liste" + jtype);
        }
    }


    public void addUseSelector(MdlFlow f) {
        if (init == null) {
            f.L________("liste", jtype, ",");
        }
    }

    public void addMdlExtraReducer(MdlFlow f) {
        if (init == null) {
            f.L________________("state.liste", jtype, " = action.payload.liste", jtype, ";");
        }
    }

    protected RefField<T> initCopy() {
        return new RefField<T>(type, isFather);
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
