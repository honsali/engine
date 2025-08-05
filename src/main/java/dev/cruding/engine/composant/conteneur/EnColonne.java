package dev.cruding.engine.composant.conteneur;

import dev.cruding.engine.composant.Composant;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.flow.ViewFlow;

public class EnColonne extends Composant {

    public int nombreColonne = 2;
    public String marge = "20";
    public String largeur;
    public String[] tableauLargeur;

    public EnColonne(Element element, Composant... listeComposant) {
        super(element, listeComposant);
        this.largeur = null;// Integer.toString(24 / nombreColonne);
    }

    public void addImport(ViewFlow flow) {
        flow.addJsImport("{ Row }", "antd");
        flow.addJsImport("{ Col }", "antd");
    }

    public boolean addOpenTag(ViewFlow flow, int level) {
        indent(flow, level).append("<Row");
        flow.totalUi().__(" gutter={").append(marge).append("}");
        flow.totalUi().__(">");

        return false;
    }

    public void addContent(Composant pereComposant, ViewFlow flow, boolean inline, int level) {
        inElement = inElement || (pereComposant != null && pereComposant.inElement);
        this.pereComposant = pereComposant;
        this.inline = inline;
        addImport(flow);
        addScript(flow);
        if (level == 1) {
            flow.totalUi().__("(");
        }
        boolean childInline = addOpenTag(flow, level);
        if (!isElement) {
            if (listeComposant != null) {
                for (int i = 0; i < listeComposant.length; i++) {
                    Composant composant = listeComposant[i];
                    if (tableauLargeur != null) {
                        indent(flow, level + 1).append("<Col flex=\"").append(tableauLargeur[i]).append("\">");
                    } else {
                        indent(flow, level + 1).append("<Col span={").append(largeur).append("}>");
                    }
                    composant.addContent(this, flow, childInline, level + 2);
                    indent(flow, level + 1).append("</Col>");
                }
            }
        }
        addCloseTag(flow, level);
        if (level == 1) {
            indent(flow, 0).append(");");
        }
    }

    public void addCloseTag(ViewFlow flow, int level) {
        indent(flow, level).append("</Row>");
    }

    public EnColonne marge(String marge) {
        this.marge = marge;
        return this;
    }

    public EnColonne largeur(int nombreColonne) {
        this.nombreColonne = nombreColonne;
        this.largeur = Integer.toString(24 / nombreColonne);
        this.tableauLargeur = null;
        return this;
    }

    public EnColonne largeur(String... tableauLargeur) {
        this.nombreColonne = tableauLargeur.length;
        this.largeur = null;
        this.tableauLargeur = tableauLargeur;
        return this;
    }

}
