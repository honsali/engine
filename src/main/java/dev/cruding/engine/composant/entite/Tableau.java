package dev.cruding.engine.composant.entite;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.action.inViewOnly.ActionSelectionDansMdl;
import dev.cruding.engine.champ.Champ;
import dev.cruding.engine.composant.Composant;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entite.Entite;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.Contexte;

public class Tableau extends Composant {

    public Action onRowClickAction;
    public Action fillFrom;
    public int largeur = 0;
    public Action actionPagination;
    public boolean selection;
    public boolean pagine = false;
    public String sourceDonnee = "liste";


    public Tableau(Element element, Entite entite, Champ... listeChamp) {
        super(element, entite, listeChamp);
        inElement = true;
        Contexte.getInstance().addLabel(element.page.module.uname, "aucun." + entite.lname, (entite.setting.feminin ? "Aucune " : "Aucun ") + entite.setting.libelle);

    }

    public Tableau gererSelection(Entite e) {
        this.selection = true;
        new ActionSelectionDansMdl(e, element);
        return this;
    }

    public Tableau siCliqueLigne(Action action) {
        action.inElement(inElement);
        action.byRow();
        this.onRowClickAction = action;
        return this;
    }

    public Tableau remplirPar(Action action) {
        if (action.actionPagination != null) {
            this.pagine = true;
            action.actionPagination.element(this.element);
        }

        sourceDonnee = action.nomVariable != null ? action.nomVariable : "liste" + (pagine ? "Paginee" : "") + entite.uname;
        action.inElement(inElement);
        action.enTantQueListe();
        this.fillFrom = action;
        if (this.fillFrom.sourceDonnee != null) {
            sourceDonnee = this.fillFrom.sourceDonnee;
        }
        return this;
    }

    public Tableau largeur(int largeur) {
        this.largeur = largeur;
        return this;
    }

    public void addImport(ViewFlow flow) {
        flow.addJsImport("{ Colonne, Tableau }", "waxant");
        flow.addSelector(sourceDonnee);
    }

    public void addScript(ViewFlow flow) {
        for (Champ c : listeChamp) {
            c.addViewScript(flow, element.page.uc, "..");
        }

        flow.totalScript().L("");
    }

    public boolean addOpenTag(ViewFlow flow, int level) {
        if (pagine) {
            indent(flow, level).append("<Tableau listeDonnee={").append(sourceDonnee).append(".liste}");
            flow.totalUi().__(" pagination={").append(sourceDonnee).append(".pagination}");
        } else {
            indent(flow, level).append("<Tableau listeDonnee={").append(sourceDonnee).append("}");
        }
        if (onRowClickAction != null) {
            flow.totalUi().__(" siClicLigne={").append(onRowClickAction.lnameAvecEntite).append("}");
        }
        if (pagine) {
            flow.totalUi().__(" siChangementPage={actionChangementPage}");
        }
        if (selection) {
            flow.totalUi().__(" siSelectionChange={changerSelection}");
        }
        flow.totalUi().__(" texteAucunResultat=\"").append("aucun.").append(entite.lname).append("\"");
        flow.totalUi().__(">");

        for (Champ c : listeChamp) {
            String prefix = c.of == null ? "" : c.of.lname + ".";
            indent(flow, level + 1).append("<").append(c.ui(Element.TABLEAU)).append(" nom=\"").append(prefix).append(c.lname).append("\"");
            if (c.libelle != null) {
                flow.totalUi().__(" libelle=\"").append(c.libelle).append("\"");
            }
            if (c.largeur > 0) {
                flow.totalUi().__(" largeur={", "" + c.largeur, "}");
            }
            flow.totalUi().__(" />");
            Contexte.getInstance().addLabelPourChamp(element.page.module.uname, c);
        }
        return false;
    }

    public void addCloseTag(ViewFlow flow, int level) {
        indent(flow, level).append("</Tableau>");
    }
}
