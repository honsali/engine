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
import dev.cruding.engine.loader.GeneratorException;
import dev.cruding.engine.printer.BePrinterException;

public class Entity extends FieldFactory {

    public String lname;
    public String uname;
    public String dbName;
    public String seqName;

    public String pkg;
    public String path;
    public String javaPackage;
    public String javaPath;

    public String key;
    public Setting id_;
    public Setting setting;
    public String lid;// lowerCaseId
    public String uid;// upperCaseId

    public boolean haveFather = false;
    public Father<?> father;
    public String lfather;
    public String ufather;

    public ArrayList<Field> fieldList = new ArrayList<>();

    public Entity() {
        this.uname = this.getClass().getSimpleName();
        this.lname = StringUtils.uncapitalize(uname);
        this.dbName = StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(uname), "_").toLowerCase();
        this.seqName = "seq_" + dbName;

        this.pkg = StringUtils.substringAfter(this.getClass().getPackageName(), "model.");
        this.path = this.pkg.replace('.', '/') + '/' + this.lname;
        this.javaPackage = (pkg + "." + lname).toLowerCase(Locale.ROOT);
        this.javaPath = javaPackage.replace('.', '/');


        this.key = UUID.nameUUIDFromBytes(this.path.getBytes(StandardCharsets.UTF_8)).toString();
        this.id_ = new Setting();
        this.id_.containingEntity(this);
        this.setting = this.id_.init(uname);
    }

    public void init() {
        Field identifier = null;

        java.lang.reflect.Field[] list = this.getClass().getFields();
        for (java.lang.reflect.Field f : list) {
            if (Field.class.isAssignableFrom(f.getType()) && f.getDeclaringClass() != Entity.class) {
                try {
                    Field field = (Field) f.get(this);
                    if (field != null) {
                        if (field.lname == null) {
                            field.lname(f.getName());
                        }
                        field.containingEntity(this);
                        if (field.isId) {
                            identifier = field;
                        }

                        if (field instanceof Ref) {
                            field.lname(f.getName());
                            fieldList.add(field);
                        } else if (field instanceof Father) {
                            if (this.father != null) {
                                throw new GeneratorException(String.format("Entity '%s' declares multiple Father fields: '%s' and '%s'. Only one Father is allowed.", uname, this.father.lname, f.getName()));
                            }
                            fieldList.add(field);
                            this.father = (Father<?>) field;
                            this.father.lname(f.getName());
                        } else if (field instanceof Setting) {
                            this.id_ = (Setting) field;
                        } else {
                            fieldList.add(field);
                        }
                    }
                } catch (IllegalAccessException e) {
                    throw new GeneratorException(String.format("Cannot access field '%s' in entity '%s'. " + "Ensure field is public and properly initialized.", f.getName(), uname), e);
                } catch (ClassCastException e) {
                    throw new GeneratorException(String.format("Field '%s' in entity '%s' is not a valid Field type.", f.getName(), uname), e);
                }
            }
        }
        validateFields();


        this.lid = identifier == null ? "id" : identifier.lname;
        this.uid = identifier == null ? "Id" : identifier.uname;

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
            throw new GeneratorException(String.format("Entity '%s' has no Father defined. Cannot get idFather.", uname));
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
