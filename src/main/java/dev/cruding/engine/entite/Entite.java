package dev.cruding.engine.entite;

import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.UUID;
import org.apache.commons.lang3.StringUtils;
import dev.cruding.engine.champ.Champ;
import dev.cruding.engine.champ.impl.GrandPere;
import dev.cruding.engine.champ.impl.Pere;
import dev.cruding.engine.champ.impl.Ref;
import dev.cruding.engine.champ.impl.RefMany;
import dev.cruding.engine.champ.impl.Setting;
import dev.cruding.engine.gen.Contexte;

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

    public ArrayList<Champ> listeChamp = new ArrayList<>();

    public Entite() {
        this.uname = this.getClass().getSimpleName();
        this.lname = StringUtils.uncapitalize(uname);
    }

    public void init() {
        Champ tempPere = null;
        Champ grandPere = null;
        Champ identifiant = null;
        Setting tempId = null;

        this.key = UUID.nameUUIDFromBytes((pkg + "/" + uname).getBytes(StandardCharsets.UTF_8)).toString();
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
                        if (field.isId) {
                            identifiant = field;
                        }

                        if (field instanceof Ref) {
                            field.lname(f.getName());
                            listeChamp.add(field);
                        } else if (field instanceof RefMany) {
                            field.lname(f.getName());
                            listeChamp.add(field);
                            haveRefMany = true;
                        } else if (field instanceof Pere) {
                            tempPere = field.lname(f.getName());
                            this.lpere = tempPere.lname;
                            listeChamp.add(field);
                        } else if (field instanceof GrandPere) {
                            grandPere = field.lname(f.getName());
                            this.lgrandPere = grandPere.lname;
                            listeChamp.add(field);
                        } else if (field instanceof Setting) {
                            tempId = (Setting) field;
                        } else {
                            listeChamp.add(field);
                        }
                    }
                } catch (IllegalAccessException e) {
                    throw new EntityInitializationException(String.format("Cannot access field '%s' in entity '%s'. " + "Ensure field is public and properly initialized.", f.getName(), uname), e);
                } catch (ClassCastException e) {
                    throw new EntityInitializationException(String.format("Field '%s' in entity '%s' is not a valid Champ type.", f.getName(), uname), e);
                }
            }
        }

        this.pere = (Pere<?>) tempPere;
        this.id_ = tempId != null ? tempId : new Setting();
        this.setting = this.id_.init(uname);
        if (identifiant == null) {
            this.lid = "id";
            this.uid = "Id";
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

    public String idPere() {
        if (pere == null) {
            throw new EntityInitializationException(String.format("Entity '%s' has no Pere defined. Cannot get idPere.", uname));
        }
        return "Id" + upere;
    }
}
