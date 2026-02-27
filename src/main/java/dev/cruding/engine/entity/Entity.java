package dev.cruding.engine.entity;


import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.UUID;
import org.apache.commons.lang3.StringUtils;
import dev.cruding.engine.field.Field;
import dev.cruding.engine.field.impl.Father;
import dev.cruding.engine.field.impl.Ref;
import dev.cruding.engine.field.impl.RefMany;
import dev.cruding.engine.field.impl.Setting;
import dev.cruding.engine.gen.DbNameMapper;

public class Entity extends FieldFactory {


    public String pkg;
    public String path;
    public String key;
    public String lid;// lowerCaseId
    public String uid;// upperCaseId
    public String lname;
    public String uname;
    public String dbName;
    public String seqName;
    public boolean haveFather = false;
    public Setting id_;
    public Father<?> father;
    public String lfather;
    public String ufather;

    public Setting setting;

    public ArrayList<Field> fieldList = new ArrayList<>();

    public Entity() {
        this.uname = this.getClass().getSimpleName();
        this.lname = StringUtils.uncapitalize(uname);
        this.pkg = StringUtils.substringAfter(this.getClass().getPackageName(), "model.");
        this.path = this.pkg.replace('.', '/') + '/' + this.lname;
        this.key = UUID.nameUUIDFromBytes(this.path.getBytes(StandardCharsets.UTF_8)).toString();
    }

    public void init() {
        Field identifier = null;
        this.id_ = new Setting();


        this.dbName = DbNameMapper.getInstance().getTableName(uname);
        this.seqName = DbNameMapper.getInstance().getSequenceName(uname);

        java.lang.reflect.Field[] list = this.getClass().getFields();
        for (java.lang.reflect.Field f : list) {
            if (Field.class.isAssignableFrom(f.getType())) {
                try {
                    Field field = (Field) f.get(this);
                    if (field != null) {
                        field.containingEntity(this);
                        if (field.isId) {
                            identifier = field;
                        }

                        if (field instanceof Ref) {
                            field.lname(f.getName());
                            fieldList.add(field);
                        } else if (field instanceof RefMany) {
                            field.lname(f.getName());
                            fieldList.add(field);
                        } else if (field instanceof Father) {
                            if (this.father == null) {
                                fieldList.add(field);
                                this.father = (Father<?>) field;
                            }
                        } else if (field instanceof Setting) {
                            this.id_ = (Setting) field;
                        } else {
                            fieldList.add(field);
                        }
                    }
                } catch (IllegalAccessException e) {
                    throw new EntityInitializationException(String.format("Cannot access field '%s' in entity '%s'. " + "Ensure field is public and properly initialized.", f.getName(), uname), e);
                } catch (ClassCastException e) {
                    throw new EntityInitializationException(String.format("Field '%s' in entity '%s' is not a valid Field type.", f.getName(), uname), e);
                }
            }
        }

        this.setting = this.id_.init(uname);

        if (identifier == null) {
            this.lid = "id";
            this.uid = "Id";
        } else {
            this.lid = identifier.lname;
            this.uid = identifier.uname;
        }

        if (this.father != null) {
            this.haveFather = true;
            this.lfather = this.father.lname;
            this.ufather = StringUtils.capitalize(lfather);
        }
    }

    public boolean isReferenceData() {
        return false;
    }

    public String idFather() {
        if (father == null) {
            throw new EntityInitializationException(String.format("Entity '%s' has no Father defined. Cannot get idFather.", uname));
        }
        return "Id" + ufather;
    }
}
