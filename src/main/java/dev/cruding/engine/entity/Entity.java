package dev.cruding.engine.entity;

import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;

import dev.cruding.engine.field.Field;
import dev.cruding.engine.field.impl.Father;
import dev.cruding.engine.field.impl.GrandFather;
import dev.cruding.engine.field.impl.Ref;
import dev.cruding.engine.field.impl.RefMany;
import dev.cruding.engine.field.impl.RefManyToMany;
import dev.cruding.engine.field.impl.Setting;
import dev.cruding.engine.gen.Context;
import dev.cruding.engine.service.Service;

public class Entity extends FieldFactory {

    public String module;
    public String modulePath;
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

    public ArrayList<Field> fieldList = new ArrayList<>();
    public ArrayList<Service> serviceList = new ArrayList<>();

    public Entity() {
        this.uname = this.getClass().getSimpleName();
        this.lname = StringUtils.uncapitalize(uname);
    }

    public void init() {
        Field tempFather = null;
        Field tempGrandFather = null;
        Field tempIdentifiant = null;
        Setting tempId = null;
        this.key = String.valueOf(Math.abs(lname.hashCode()));
        this.module = StringUtils.substringAfter(this.getClass().getPackageName(), "modele.");
        this.modulePath = this.module.replace('.', '/');

        this.dbName = Context.getInstance().getLegacyDbName(uname, "table", StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(lname), "_").toLowerCase());
        this.seqName = Context.getInstance().getLegacyDbName(uname, "sequence", "seq_" + this.dbName);
        for (java.lang.reflect.Field f : this.getClass().getFields()) {
            if (Field.class.isAssignableFrom(f.getType())) {
                try {
                    Field field = (Field) f.get(this);
                    if (field != null) {
                        field.containingEntity(this);
                        if (field instanceof Ref) {
                            field.lname(f.getName());
                            fieldList.add(field);
                        } else if (field instanceof RefManyToMany) {
                            field.lname(f.getName());
                            fieldList.add(field);
                            haveRefMany = true;
                        } else if (field instanceof RefMany) {
                            field.lname(f.getName());
                            fieldList.add(field);
                            haveRefMany = true;
                        } else if (field instanceof Father) {
                            tempFather = field.lname(f.getName());
                            field.containingEntity(this);
                            this.lfather = tempFather.lname;
                            fieldList.add(field);
                        } else if (field instanceof GrandFather) {
                            tempGrandFather = field.lname(f.getName());
                            field.containingEntity(this);
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
        //this.id_.containingEntity(this);
        Field identifiant = tempIdentifiant;
        if (identifiant == null) {
            System.out.println("L'entity " + uname + " n'a pas d'identifiant");
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