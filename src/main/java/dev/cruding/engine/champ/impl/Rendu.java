package dev.cruding.engine.champ.impl;

import dev.cruding.engine.champ.Champ;
import dev.cruding.engine.element.ElementPrinter;
import dev.cruding.engine.flow.ViewFlow;

public class Rendu extends Champ {

    public Rendu(Champ f) {
        super(f);
    }

    public Rendu(String lname) {
        super(true);
        lname(lname);
    }

    public void addViewScript(ViewFlow f, String uc, String mvcPath) {
        f.totalScript().L____("const getColonne", uname, " = (texte, element) => {");
        f.totalScript().L________("return <div>{element.", lname, "}</div>;");
        f.totalScript().L____("};");
        f.totalScript().L("");
    }

    public String ui(String element) {
        if (element.equals(ElementPrinter.TABLEAU)) {
            return "Colonne tc=\"rendu\" content={getColonne" + uname + "}";
        }
        return super.ui(element);

    }

    protected Champ initCopy() {
        return new Rendu(lname);
    }
}
