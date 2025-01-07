package dev.cruding.engine.champ.impl;

import dev.cruding.engine.champ.Champ;
import dev.cruding.engine.composant.bouton.Bouton;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.flow.ViewFlow;

public class ColonneAction extends Champ {


    private Bouton bouton;

    public ColonneAction(Bouton b) {
        super(true);
        lname(b.actionnable.lname);
        this.bouton = b;
    }

    public void addViewScript(ViewFlow f, String uc, String mvcPath) {
        f.totalScript().L("");
        f.totalScript().L____("const getColonne", uname, " = (texte, element) => {");
        f.totalScript().L________("return ");
        bouton.addInlineContent(f, true);
        f.totalScript().__(";");
        f.totalScript().L____("};");
        f.totalScript().__("");
    }

    public String ui(String element) {
        if (element.equals(Element.TABLEAU)) {
            return "Colonne tc=\"custom\" content={getColonne" + uname + "}";
        }
        return super.ui(element);

    }

}
