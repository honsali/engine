package dev.cruding.engine.entity;


import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import org.apache.commons.lang3.StringUtils;
import dev.cruding.engine.field.Field;
import dev.cruding.engine.field.impl.Father;
import dev.cruding.engine.field.impl.Ref;
import dev.cruding.engine.field.impl.Setting;
import dev.cruding.engine.gen.Context;
import dev.cruding.engine.printer.BePrinterException;

public class Entity extends FieldFactory {


    public record DateOrderConstraint(String name, Field begin, Field end) {
    }

    public String pkg;
    public String path;
    public String key;
    public String lid;// lowerCaseId
    public String uid;// upperCaseId
    public String lname;
    public String uname;
    public String dbName;
    public String seqName;
    private String apiCollectionName;
    public boolean haveFather = false;
    public Setting id_;
    public Father<?> father;
    public String lfather;

    public String ufather;

    public Setting setting;
    public ArrayList<Field> fieldList = new ArrayList<>();

    public ArrayList<DateOrderConstraint> dateOrderConstraints = new ArrayList<>();
    private Context context;

    public Entity() {
        this.uname = this.getClass().getSimpleName();
        this.lname = StringUtils.uncapitalize(uname);
        this.pkg = StringUtils.substringAfter(this.getClass().getPackageName(), "model.");
        this.path = this.pkg.replace('.', '/') + '/' + this.lname;
        this.key = UUID.nameUUIDFromBytes(this.path.getBytes(StandardCharsets.UTF_8)).toString();
        this.apiCollectionName = this.lname + "s";
    }

    public void init() {
        Field identifier = null;
        this.id_ = new Setting();


        this.dbName = context().getDbNameMapper().getTableName(uname);
        this.seqName = context().getDbNameMapper().getSequenceName(uname);

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
                        } else if (field instanceof Father) {
                            if (this.father == null) {
                                fieldList.add(field);
                                this.father = (Father<?>) field;
                                this.father.lname(f.getName());
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
        validateFields();
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

    public void attachTo(Context context) {
        if (context == null) {
            throw new EntityInitializationException("Entity Context cannot be null: " + uname);
        }
        if (this.context != null && this.context != context) {
            throw new EntityInitializationException("Entity already belongs to another Context: " + uname);
        }
        this.context = context;
    }

    public Context context() {
        if (context == null) {
            throw new EntityInitializationException("Entity is not attached to a Context: " + uname);
        }
        return context;
    }

    public boolean isReferenceData() {
        return false;
    }

    public String apiDomainPath() {
        return "/" + pkg.replace('.', '/');
    }

    public String apiCollectionName() {
        return apiCollectionName;
    }

    protected void apiCollectionName(String apiCollectionName) {
        this.apiCollectionName = apiCollectionName;
    }

    public String apiCollectionPath() {
        return apiDomainPath() + "/" + apiCollectionName;
    }

    public String javaPackage() {
        return (pkg + "." + lname).toLowerCase(Locale.ROOT);
    }

    public String javaPath() {
        return javaPackage().replace('.', '/');
    }

    public String idFather() {
        if (father == null) {
            throw new EntityInitializationException(String.format("Entity '%s' has no Father defined. Cannot get idFather.", uname));
        }
        return "Id" + ufather;
    }

    public List<Field> listAllFieldButFather() {
        return fieldList.stream().filter(field -> !field.isFather).toList();
    }

    public List<Field> listRef() {
        return fieldList.stream().filter(field -> field.isRef).toList();
    }

    public List<Field> listRefAndFather() {
        return fieldList.stream().filter(field -> field.isRef || field.isFather).toList();
    }


    private void validateFields() {
        List<Field> fields = fieldList.stream().filter(field -> field.isBasic || field.isRef || field.isFather).toList();
        if (fields.isEmpty()) {
            throw new BePrinterException("Entity '" + uname + "' has no persistent fields.");
        }
        List<Field> identifiers = fields.stream().filter(field -> field.isId).toList();
        if (identifiers.size() != 1) {
            throw new BePrinterException("Entity '" + uname + "' must have exactly one identifier field.");
        }
        if (!"String".equals(identifiers.getFirst().jtype)) {
            throw new BePrinterException("Identifier field '" + identifiers.getFirst().lname + "' must use Java type String.");
        }

    }

}
