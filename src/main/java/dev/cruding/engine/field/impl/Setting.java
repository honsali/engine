package dev.cruding.engine.field.impl;

import org.apache.commons.lang3.StringUtils;
import dev.cruding.engine.field.Field;

public class Setting extends Field {

    public boolean readOnly = false;
    public boolean feminine = false;
    public boolean vowel = false;

    public Setting() {
        super(true);
        jtype("Long").jstype("string").stype("bigint");
        lname("id");
    }

    public Setting init(String uname) {
        if (label == null) {
            label = StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(uname), " ");
        }
        vowel = uname.startsWith("A") || uname.startsWith("E") || uname.startsWith("I") || uname.startsWith("O") || uname.startsWith("U");

        return this;
    }

    public Setting readOnly() {
        Setting s = makeCopy();
        s.readOnly = true;
        return s;
    }

    public Setting feminine() {
        Setting s = makeCopy();
        s.feminine = true;
        return s;
    }

    public Setting vowel() {
        Setting s = makeCopy();
        s.vowel = true;
        return s;
    }

    public String that() {
        if (vowel && !feminine) {
            return "cet";
        } else if (feminine) {
            return "cette";
        }
        return "ce";
    }


    protected Setting initCopy() {
        return new Setting();
    }

    protected Setting makeCopy() {
        Setting s = initCopy();
        s.readOnly = this.readOnly;
        s.feminine = this.feminine;
        s.vowel = this.vowel;
        return copyFieldProps(this, s);

    }

}
