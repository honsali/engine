
package dev.cruding.engine.composant.entite;

import dev.cruding.engine.champ.Champ;
import dev.cruding.engine.composant.Composant;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entite.Entite;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.Contexte;
import dev.cruding.engine.gen.helper.Util;

public class Etat extends Composant {

    public int nombreColonne = 2;
    public int largeur = 0;
    public Champ field;

    public Etat(Entite entite, Element element, Champ... listeChamp) {
        super(element, entite, listeChamp);
    }

    public Etat(Champ field, Entite entite, Element element, Champ... listeChamp) {
        super(element, entite, listeChamp);
        this.field = field;
    }

    public void addImport(ViewFlow flow) {
        for (Champ c : listeChamp) {
            if (c.videSi != null) {
                flow.addJsImport("{ ChampVide }", "waxant");
            }
        }

        StringBuilder fieldImportList = Util.processListeChamp(listeChamp, Element.DETAIL);

        flow.addJsImport("{ FormulaireConsultation }", "waxant");
        flow.addJsImport(" { " + fieldImportList.toString() + " } ", "waxant");
    }

    public void addScript(ViewFlow flow) {
        flow.addSelector(entite.lname);
    }

    public boolean addOpenTag(ViewFlow flow, int level) {
        if (field != null) {
            indent(flow, level).append("<FormulaireConsultation modele={" + entite.lname + "." + field.lname + "}");
        } else {
            indent(flow, level).append("<FormulaireConsultation modele={" + entite.lname + "}");
        }
        if (nombreColonne != 2) {
            flow.totalUi().__(" nombreColonne={" + nombreColonne + "}");
        }
        flow.totalUi().__(">");
        for (Champ c : listeChamp) {
            indent(flow, level + 1).append("<" + c.ui(Element.DETAIL) + " nom=\"" + c.lname + "\"");
            if (c.libelle != null) {
                flow.totalUi().__(" libelle=\"" + c.libelle + "\"");
            }
            if (c.largeur > 0) {
                flow.totalUi().__(" width={" + c.largeur + "}");
            }
            flow.totalUi().__(c.getExtension());
            if (c.invisibleSi != null) {
                flow.totalUi().__(" invisible={" + c.invisibleSi + "}");
            }
            if (c.videSi != null) {
                flow.totalUi().__(" invisible={" + c.videSi + "}");
            }
            if (c.seulDansLaLigne) {
                flow.totalUi().__(" seulDansLaLigne");
            }
            if (c.surTouteLaLigne) {
                flow.totalUi().__(" surTouteLaLigne");
            }
            flow.totalUi().__(" />");
            if (c.videSi != null && nombreColonne == 2) {
                indent(flow, level + 1).append("<ChampVide invisible={!" + c.videSi + "} />");
            }
            Contexte.getInstance().addLabelPourChamp(element.page.module.uname, c);
        }
        return false;
    }

    public void addCloseTag(ViewFlow flow, int level) {
        indent(flow, level).append("</FormulaireConsultation>");
    }

    public Etat nombreColonne(int nombreColonne) {
        this.nombreColonne = nombreColonne;
        return this;
    }

    public Etat largeur(int largeur) {
        this.largeur = largeur;
        return this;
    }

}
