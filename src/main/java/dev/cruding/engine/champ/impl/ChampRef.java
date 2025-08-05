package dev.cruding.engine.champ.impl;

import org.apache.commons.lang3.StringUtils;
import dev.cruding.engine.champ.Champ;
import dev.cruding.engine.entite.Entite;
import dev.cruding.engine.flow.CtrlFlow;
import dev.cruding.engine.flow.Flow;
import dev.cruding.engine.flow.JsFlow;
import dev.cruding.engine.flow.MdlFlow;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.Contexte;

public class ChampRef<T extends Entite> extends Champ {

    protected Class<T> type;
    public T referencedEntite;
    public String dbTypeName;
    public String jtDbName;
    public String jcDbName;
    public String ijcDbName;

    public ChampRef(Class<T> type, boolean many, String lname) {
        super(false);
        this.type = type;
        isRefMany = many;
        lname(lname).jtype(type.getSimpleName());
    }

    public ChampRef(Class<T> type, boolean many) {
        this(type, many, StringUtils.uncapitalize(type.getSimpleName()));
    }

    public ChampRef<?> lname(String lname) {
        this.lname = lname;
        this.uname = StringUtils.capitalize(lname);
        this.dbTypeName = StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(type.getSimpleName()), "_").toLowerCase();
        this.dbName = StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(lname), "_").toLowerCase() + "_id";
        if (containingEntite != null && (isRef || isRefMany || isPere)) {
            this.jtDbName = Contexte.getInstance().getLegacyDbName(containingEntite, lname, "joinTable", containingEntiteDbname + "_" + this.dbTypeName);
            this.jcDbName = Contexte.getInstance().getLegacyDbName(containingEntite, lname, "joinColumn", dbName);
            this.ijcDbName = Contexte.getInstance().getLegacyDbName(containingEntite, lname, "inverseJoinColumn", this.dbTypeName);
        }
        return this;
    }

    public void addJsImport(JsFlow f, Entite entite) {
        if (this.referencedEntite != null && !entite.uname.equals(this.referencedEntite.uname)) {
            f.addJsImport("{ I" + this.referencedEntite.uname + " }", "modele/" + this.referencedEntite.path + "/Domaine" + this.referencedEntite.uname);
        }
    }

    public Champ containingEntite(Entite entite) {
        this.referencedEntite = (T) Contexte.getInstance().getEntite(jtype);
        jstype("I" + this.referencedEntite.uname + (isRefMany ? "[]" : ""));

        this.containingEntite = entite.uname;
        this.containingEntiteDbname = entite.dbName;

        return this;
    }

    public void addLiqDeclaration(Flow f) {
        f.L____________("<column name=\"" + dbName + "\" type=\"bigint\">");
        f.L________________("<constraints nullable=\"" + !requis + "\" />");
        f.L____________("</column>");
    }

    public ChampRef<T> dbName(String dbName) {
        ChampRef<T> p = makeCopy();
        p.dbName = dbName;
        p.dbTypeName = dbName.endsWith("_id") ? dbName.substring(0, -3) : dbName;
        return p;
    }

    public void addCtrlImport(CtrlFlow f) {
        if (init == null) {
            Entite entite = Contexte.getInstance().getEntite(jtype);
            f.addCtrlImport("Service" + entite.uname, "modele/" + entite.path + "/Service" + entite.uname);
        }
    }

    public void addCtrlImplementation(CtrlFlow f) {
        if (init == null) {
            Entite entite = Contexte.getInstance().getEntite(jtype);
            f.L____("resultat.liste", jtype, " = await Service", jtype);
            if (entite.havePere) {
                f.__(".listerParId", entite.upere, "(requete.id" + entite.upere, ");");
            } else {
                f.__(".lister();");
            }

        }
    }

    public void addViewScript(ViewFlow f, String uc, String mvcPath) {
        if (initFromMdl) {
            f.addSelector("liste" + jtype);
        }
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

    protected ChampRef<T> initCopy() {
        return new ChampRef<T>(type, isRefMany);
    }

    protected ChampRef<T> makeCopy() {
        ChampRef<T> p = initCopy();
        p.jtDbName = this.jtDbName;
        p.jcDbName = this.jcDbName;
        p.ijcDbName = this.ijcDbName;
        p.dbTypeName = this.dbTypeName;
        return copyChampProps(this, p);
    }

}
