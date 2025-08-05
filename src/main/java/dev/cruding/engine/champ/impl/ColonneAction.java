package dev.cruding.engine.champ.impl;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.champ.Champ;
import dev.cruding.engine.composant.bouton.Bouton;
import dev.cruding.engine.element.ComposantRepresentantUnElement;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entite.Entite;
import dev.cruding.engine.flow.ViewFlow;

public class ColonneAction extends Champ {

    private Bouton composant;
    private Action action;
    private ComposantRepresentantUnElement composantRepresentantUnElement;

    public ColonneAction() {
        super(true);
    }

    public ColonneAction(Entite entite, Bouton b) {
        super(true);
        lname(b.action.lcoreName);
        this.composant = b;
        this.composant.action.parProp(entite.lname + ":element");
    }

    public ColonneAction(Entite entite, String lname, ComposantRepresentantUnElement c) {
        super(true);
        lname(lname);
        this.composantRepresentantUnElement = c;
        this.composantRepresentantUnElement.parProp(entite.lname + ":element");
    }

    public ColonneAction(Entite entite, Action action) {
        super(true);
        lname(action.lnameAvecEntite);
        this.action = action;
    }

    public void addViewScript(ViewFlow f, String uc, String mvcPath) {
        if (action == null) {
            f.totalScript().L("");
            f.totalScript().L____("const getColonne", uname, " = (texte, element) => {");
            f.totalScript().L________("return ");
            if (composant != null) {
                composant.addImport(f);
                composant.appendContent(f, f.totalScript());
            } else if (composantRepresentantUnElement != null) {
                composantRepresentantUnElement.appendContent(f, f.totalScript());
            }
            f.totalScript().__(";");
            f.totalScript().L____("};");
            f.totalScript().__("");
        }
    }

    public String ui(String element) {
        if (element.equals(Element.TABLEAU)) {
            return "Colonne tc=\"custom\" content={getColonne" + uname + "}";
        }
        return super.ui(element);

    }


    protected ColonneAction initCopy() {
        return new ColonneAction();
    }

    protected ColonneAction makeCopy() {
        ColonneAction p = initCopy();
        p.composant = this.composant;
        p.action = this.action;
        p.composantRepresentantUnElement = this.composantRepresentantUnElement;
        return copyChampProps(this, p);
    }


}
