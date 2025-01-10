package dev.cruding.engine.composant.entite;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.champ.Champ;
import dev.cruding.engine.champ.impl.ChampRef;
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
    public Action selection;
    public boolean pagine = false;
    public String sourceDonnee = "liste";


    public Tableau(Element element, Entite entite, Champ... fieldList) {
        this(element, entite, null, fieldList);
    }

    public Tableau(Element element, Entite entite, Action action, Champ... fieldList) {
        super(element, entite, fieldList);
        inElement = true;
        /*
         * if (action != null) { this.actionPagination = new ActionChangerPage(ActionType.NOUI,
         * "changerPage", entite, element).action(action); this.pagine = actionPagination != null; if
         * (this.actionPagination != null) { actionPagination.inElement(inElement); } }
         */
        sourceDonnee = "liste" + (pagine ? "Paginee" : "") + entite.uname;
        Contexte.getInstance().addLabel(element.page.module.uname, "aucun." + entite.lname, (entite.setting.feminin ? "Aucune " : "Aucun ") + entite.setting.libelle);
    }

    public Tableau onRowClick(Action action) {
        action.inElement(inElement);
        action.byRow();
        this.onRowClickAction = action;
        return this;
    }

    public Tableau fillFrom(Action action) {
        action.inElement(inElement);
        action.paginee(this.pagine);
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
        flow.addJsImport("{ Bloc, Colonne, Tableau }", "waxant");
        flow.addSpecificSelector(sourceDonnee, "../Mdl" + element.page.uc);
        if (selection != null) {
            flow.useDispatch();
        }
    }

    public void addScript(ViewFlow flow) {
        for (Champ c : fieldList) {
            if (c instanceof ChampRef) {
                ((ChampRef) c).addViewScript(flow, element.page.uc, "..");
            }
        }
        if (selection != null) {
            flow.totalScript().L____("const changerSelection = (liste) => {");
            flow.totalScript().L________("dispatch(Mdl", element.page.uc, ".modifier", selection.uname, "(liste));");
            flow.totalScript().L____("};");
        }
        flow.totalScript().L("");
    }

    public boolean addOpenTag(ViewFlow flow, int level) {
        if (pagine) {
            indent(flow, level).append("<Tableau listeDonnee={").append(sourceDonnee).append(".liste}");
            flow.addToUi(" pagination={").append(sourceDonnee).append(".pagination}");
        } else {
            indent(flow, level).append("<Tableau listeDonnee={").append(sourceDonnee).append("}");
        }
        if (onRowClickAction != null) {
            flow.addToUi(" siClicLigne={").append(onRowClickAction.lname).append("}");
        }
        if (pagine) {
            flow.addToUi(" siChangementPage={actionChangementPage}");
        }
        if (selection != null) {
            flow.addToUi(" siSelectionChange={changerSelection}");
        }
        flow.addToUi(" texteAucunResultat=\"").append("aucun.").append(entite.lname).append("\"");
        flow.addToUi(">");

        for (Champ c : fieldList) {
            String prefix = c.of == null ? "" : c.of.lname + ".";
            indent(flow, level + 1).append("<").append(c.ui(Element.TABLEAU)).append(" nom=\"").append(prefix).append(c.lname).append("\"");
            if (c.libelle != null) {
                flow.addToUi(" libelle=\"").append(c.libelle).append("\"");
            }
            if (c.width > 0) {
                flow.addToUi(" width={").append(c.width).append("}");
            }
            flow.addToUi(" />");
        }
        return false;
    }

    public void addCloseTag(ViewFlow flow, int level) {
        indent(flow, level).append("</Tableau>");
    }
}
