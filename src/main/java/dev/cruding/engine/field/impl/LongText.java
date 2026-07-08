package dev.cruding.engine.field.impl;

import dev.cruding.engine.element.Element;
import dev.cruding.engine.field.Field;
import dev.cruding.engine.flow.JavaFlow;

public class LongText extends Field {

    public LongText(String lname) {
        super(true);
        lname(lname).jtype("String").jstype("string").stype("text");
        isText = true;
    }

    public void addJavaDeclaration(JavaFlow f) {
        f.L("");
        if (required) {
            f.L____("@NotBlank");
        }
        if (tranzient) {
            f.L____("@Transient");
        } else {
            f.L____("@Column(name = \"" + dbName + "\", columnDefinition = \"text\"");
            if (required) {
                f.__(", nullable = false");
            }
            f.__(")");
        }
        f.L____("private " + jtype + " " + lname + ";");
    }

    public String ui(String element) {
        switch (element) {
            case Element.FORM:
                return "ChampTexteLong";
            case Element.DETAIL:
                return "Texte";
            case Element.TABLE:
                return "Colonne";
            default:
                return "";
        }
    }

    protected Field initCopy() {
        return new LongText(lname);
    }
}
