
package dev.cruding.engine.composant.entite;

import dev.cruding.engine.champ.Champ;
import dev.cruding.engine.composant.Composant;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entite.Entite;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.helper.Util;

public class Etat extends Composant {

    public int colNumber = 2;
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
        flow.addSpecificSelector(entite.lname, "../Mdl" + element.page.uc);
    }

    public boolean addOpenTag(ViewFlow flow, int level) {
        if (field != null) {
            indent(flow, level).append("<FormulaireConsultation modele={" + entite.lname + "." + field.lname + "}");
        } else {
            indent(flow, level).append("<FormulaireConsultation modele={" + entite.lname + "}");
        }
        if (colNumber != 2) {
            flow.addToUi(" nombreColonne={" + colNumber + "}");
        }
        flow.addToUi(">");
        for (Champ c : listeChamp) {
            indent(flow, level + 1).append("<" + c.ui(Element.DETAIL) + " nom=\"" + c.lname + "\"");
            if (c.libelle != null) {
                flow.addToUi(" libelle=\"" + c.libelle + "\"");
            }
            if (c.width > 0) {
                flow.addToUi(" width={" + c.width + "}");
            }
            flow.addToUi(c.getExtension());
            if (c.invisibleSi != null) {
                flow.addToUi(" invisible={" + c.invisibleSi + "}");
            }
            if (c.videSi != null) {
                flow.addToUi(" invisible={" + c.videSi + "}");
            }
            if (c.seulDansLaLigne) {
                flow.addToUi(" seulDansLaLigne");
            }
            if (c.surTouteLaLigne) {
                flow.addToUi(" surTouteLaLigne");
            }
            flow.addToUi(" />");
            if (c.videSi != null && colNumber == 2) {
                indent(flow, level + 1).append("<ChampVide invisible={!" + c.videSi + "} />");
            }
        }
        return false;
    }

    public void addCloseTag(ViewFlow flow, int level) {
        indent(flow, level).append("</FormulaireConsultation>");
    }

    public Etat colNumber(int colNumber) {
        this.colNumber = colNumber;
        return this;
    }

    public Etat largeur(int largeur) {
        this.largeur = largeur;
        return this;
    }

}
