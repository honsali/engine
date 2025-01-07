package dev.cruding.engine.entite;

import java.lang.reflect.Field;
import java.util.ArrayList;
import org.apache.commons.lang3.StringUtils;
import dev.cruding.engine.champ.Champ;
import dev.cruding.engine.champ.impl.GrandPere;
import dev.cruding.engine.champ.impl.Pere;
import dev.cruding.engine.champ.impl.Ref;
import dev.cruding.engine.champ.impl.RefMany;
import dev.cruding.engine.champ.impl.Setting;
import dev.cruding.engine.gen.Contexte;
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

    public boolean havePere = false;
    public Setting id_;
    public Pere<?> pere;
    public String lpere;
    public String upere;
    public boolean haveGrandPere = false;
    public boolean haveRefMany = false;

    public String lgrandPere;
    public String ugrandPere;

    public Setting setting;

    public ArrayList<Champ> fieldList = new ArrayList<>();
    public ArrayList<Service> serviceList = new ArrayList<>();

    public Entite() {
        this.uname = this.getClass().getSimpleName();
        this.lname = StringUtils.uncapitalize(uname);
    }

    public void init() {
        Champ tempPere = null;
        Champ tempGrandPere = null;
        Champ tempIdentifiant = null;
        Setting tempId = null;
        this.key = String.valueOf(Math.abs(lname.hashCode()));
        this.pkg = StringUtils.substringAfter(this.getClass().getPackageName(), "modele.");
        this.path = this.pkg.replace('.', '/') + '/' + this.lname;

        this.dbName = Contexte.getInstance().getLegacyDbName(uname, "table", StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(lname), "_").toLowerCase());
        this.seqName = Contexte.getInstance().getLegacyDbName(uname, "sequence", "seq_" + this.dbName);
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
                        } else if (field instanceof Pere) {
                            tempPere = field.lname(f.getName());
                            this.lpere = tempPere.lname;
                            fieldList.add(field);
                        } else if (field instanceof GrandPere) {
                            tempGrandPere = field.lname(f.getName());
                            this.lgrandPere = tempGrandPere.lname;
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
        this.pere = (Pere<?>) tempPere;
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
        if (this.lpere != null) {
            this.havePere = true;
            this.upere = StringUtils.capitalize(lpere);
            if (this.lgrandPere != null) {
                this.haveGrandPere = true;
                this.ugrandPere = StringUtils.capitalize(lgrandPere);

            }
        }

    }

    public boolean isReferenceData() {
        return false;
    }

}
