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

    public Field Text() {
        return new Text();
    }

    public Field ArabicText() {
        return new ArabicText();
    }

    public Field Year() {
        return new Year();
    }

    public Field Double() {
        return new Double();
    }

    public Field Int() {
        return new Int();
    }

    public Field Date() {
        return new Date();
    }

    public Field Tel() {
        return new Tel();
    }

    public Field Email() {
        return new Email();
    }

    public Field Hour() {
        return new Hour();
    }

    public Field File() {
        return new File();
    }

    public StaticList StaticList() {
        return new StaticList();
    }


    public <T extends Entity> Father<T> Father(Class<T> type) {
        return new Father<T>(type);
    }

    public <T extends Entity> Ref<T> Ref(Class<T> type) {
        return new Ref<T>(type);
    }


    public Field TextArray() {
        return new TextArray();
    }

    public Field Boolean() {
        return new Boolean();
    }

    public Field LongText() {
        return new LongText();
    }

}
