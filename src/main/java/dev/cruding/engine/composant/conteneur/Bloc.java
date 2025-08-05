package dev.cruding.engine.composant.conteneur;

import dev.cruding.engine.composant.Composant;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.flow.ViewFlow;

public class Bloc extends Conteneur {

    public String largeur = null;
    public String marge = null;
    public String fond = null;

    public Bloc(Element element, Composant... listeComposant) {
        super(element, listeComposant);
    }

    public void addImport(ViewFlow flow) {
        flow.addJsImport("{ Bloc }", "waxant");
    }

    public boolean addOpenTag(ViewFlow flow, int level) {
        indent(flow, level).append("<Bloc");
        if (largeur != null) {
            flow.totalUi().__(" largeur=\"").append(largeur).append("\"");
        }
        if (marge != null) {
            flow.totalUi().__(" marge=\"").append(marge).append("\"");
        }
        if (fond != null) {
            flow.totalUi().__(" fond=\"").append(fond).append("\"");
        }
        flow.totalUi().__(">");
        return false;
    }

    public void addCloseTag(ViewFlow flow, int level) {
        indent(flow, level).append("</Bloc>");
    }

    public Bloc fond(String fond) {
        this.fond = fond;
        return this;
    }

    public Bloc marge(String marge) {
        this.marge = marge;
        return this;
    }

    public Bloc largeur(String largeur) {
        this.largeur = largeur;
        return this;
    }

}
