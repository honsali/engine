package dev.cruding.engine.field.impl;

import dev.cruding.engine.component.bouton.Bouton;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.field.Field;
import dev.cruding.engine.flow.ViewFlow;

public class ColonneAction extends Field {


    private Bouton bouton;

    public ColonneAction(Bouton b) {
        super(true);
        lname(b.lname);
        this.bouton = b;
    }

    public void addViewScript(ViewFlow f, String uc, String mvcPath) {
        f.totalScript().L("");
        f.totalScript().L____("const getColonne", uname, " = (value, ", bouton.entity.lname, ") => {");
        f.totalScript().L________("return ");
        bouton.addImport(f);
        bouton.addInlineTag(f.totalScript());
        f.totalScript().__(";");
        f.totalScript().L____("};");
    }

    public String ui(String element) {
        if (element.equals(Element.TABLEAU)) {
            return "Colonne tc=\"custom\" content={getColonne" + uname + "}";
        }
        return super.ui(element);

    }

}
