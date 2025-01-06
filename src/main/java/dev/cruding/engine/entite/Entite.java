package dev.cruding.engine.entite;

import java.lang.reflect.Field;
import java.util.ArrayList;
import org.apache.commons.lang3.StringUtils;
import dev.cruding.engine.champ.Champ;
import dev.cruding.engine.champ.impl.Father;
import dev.cruding.engine.champ.impl.GrandFather;
import dev.cruding.engine.champ.impl.Ref;
import dev.cruding.engine.champ.impl.RefMany;
import dev.cruding.engine.champ.impl.Setting;
import dev.cruding.engine.gen.Context;
import dev.cruding.engine.service.Service;

public class Entite extends ChampFactory {

    public String pkg;
    public String path;
    public String key;
    public String lid;
    public String uid;
    public String lname;
    public String uname;
    public String dbName;
    public String seqName;

    public boolean haveFather = false;
    public Setting id_;
    public Father<?> father;
    public String lfather;
    public String ufather;
    public boolean haveGrandFather = false;
    public boolean haveRefMany = false;

    public String lgrandfather;
    public String ugrandfather;

    public Setting setting;

    public ArrayList<Champ> fieldList = new ArrayList<>();
    public ArrayList<Service> serviceList = new ArrayList<>();

    public Entite() {
        this.uname = this.getClass().getSimpleName();
        this.lname = StringUtils.uncapitalize(uname);
    }

    public void init() {
        Champ tempFather = null;
        Champ tempGrandFather = null;
        Champ tempIdentifiant = null;
        Setting tempId = null;
        this.key = String.valueOf(Math.abs(lname.hashCode()));
        this.pkg = StringUtils.substringAfter(this.getClass().getPackageName(), "modele.");
        this.path = this.pkg.replace('.', '/') + '/' + this.lname;

        this.dbName = Context.getInstance().getLegacyDbName(uname, "table", StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(lname), "_").toLowerCase());
        this.seqName = Context.getInstance().getLegacyDbName(uname, "sequence", "seq_" + this.dbName);
        for (Field f : this.getClass().getFields()) {
            if (Champ.class.isAssignableFrom(f.getType())) {
                try {
                    Champ field = (Champ) f.get(this);
                    if (field != null) {
                        field.containingEntite(this);
                        if (field instanceof Ref) {
                            field.lname(f.getName());
                            fieldList.add(field);
                        } else if (field instanceof RefMany) {
                            field.lname(f.getName());
                            fieldList.add(field);
                            haveRefMany = true;
                        } else if (field instanceof Father) {
                            tempFather = field.lname(f.getName());
                            this.lfather = tempFather.lname;
                            fieldList.add(field);
                        } else if (field instanceof GrandFather) {
                            tempGrandFather = field.lname(f.getName());
                            this.lgrandfather = tempGrandFather.lname;
                            fieldList.add(field);

                        } else if (field instanceof Setting) {
                            tempId = (Setting) field;
                        } else if (field != null) {
                            fieldList.add(field);
                        }
                        if (field.isId) {
                            tempIdentifiant = field;
                        }
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        this.father = (Father<?>) tempFather;
        this.id_ = tempId != null ? tempId : new Setting();
        this.setting = this.id_.init(uname);
        Champ identifiant = tempIdentifiant;
        if (identifiant == null) {
            System.out.println("L'entite " + uname + " n'a pas d'identifiant");
            System.exit(0);
        } else {
            this.lid = identifiant.lname;
            this.uid = identifiant.uname;
        }
        if (this.lfather != null) {
            this.haveFather = true;
            this.ufather = StringUtils.capitalize(lfather);
            if (this.lgrandfather != null) {
                this.haveGrandFather = true;
                this.ugrandfather = StringUtils.capitalize(lgrandfather);

            }
        }

    }

    public boolean isReferenceData() {
        return false;
    }

}
