package dev.cruding.engine.entity;

import dev.cruding.engine.field.Field;
import dev.cruding.engine.field.impl.ArabicText;
import dev.cruding.engine.field.impl.Boolean;
import dev.cruding.engine.field.impl.Date;
import dev.cruding.engine.field.impl.Double;
import dev.cruding.engine.field.impl.Email;
import dev.cruding.engine.field.impl.Father;
import dev.cruding.engine.field.impl.File;
import dev.cruding.engine.field.impl.Hour;
import dev.cruding.engine.field.impl.Int;
import dev.cruding.engine.field.impl.LongText;
import dev.cruding.engine.field.impl.Ref;
import dev.cruding.engine.field.impl.RefMany;
import dev.cruding.engine.field.impl.Setting;
import dev.cruding.engine.field.impl.StaticList;
import dev.cruding.engine.field.impl.Tel;
import dev.cruding.engine.field.impl.Text;
import dev.cruding.engine.field.impl.TextArray;
import dev.cruding.engine.field.impl.Year;

public class FieldFactory {

    public Setting Setting() {
        return new Setting();
    }

    public Field Text(String name) {
        return new Text(name);
    }

    public Field ArabicText(String name) {
        return new ArabicText(name);
    }

    public Field Year(String name) {
        return new Year(name);
    }

    public Field Double(String name) {
        return new Double(name);
    }

    public Field Int(String name) {
        return new Int(name);
    }

    public Field Date(String name) {
        return new Date(name);
    }

    public Field Tel(String name) {
        return new Tel(name);
    }

    public Field Email(String name) {
        return new Email(name);
    }

    public Field Hour(String name) {
        return new Hour(name);
    }

    public Field File(String name) {
        return new File(name);
    }

    public StaticList StaticList(String name) {
        return new StaticList(name);
    }

    public StaticList StaticList(String name, String type) {
        return new StaticList(name, type);
    }


    public <T extends Entity> Father<T> Father(Class<T> type) {
        return new Father<T>(type);
    }

    public <T extends Entity> Ref<T> Ref(Class<T> type) {
        return new Ref<T>(type);
    }


    public <T extends Entity> Ref<T> Ref(Class<T> type, String lname) {
        return new Ref<T>(type, lname);
    }

    public <T extends Entity> RefMany<T> RefMany(Class<T> type) {
        return new RefMany<T>(type);
    }

    public <T extends Entity> RefMany<T> RefMany(Class<T> type, String lname) {
        return new RefMany<T>(type, lname);
    }

    public Field TextArray(String name) {
        return new TextArray(name);
    }

    public Field Boolean(String name) {
        return new Boolean(name);
    }

    public Field LongText(String name) {
        return new LongText(name);
    }

}
