package dev.cruding.engine.component.entite;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.action.impl.ActionChangerSelection;
import dev.cruding.engine.champ.Champ;
import dev.cruding.engine.champ.impl.RefChamp;
import dev.cruding.engine.component.Component;
import dev.cruding.engine.component.bouton.Actionnable;
import dev.cruding.engine.component.bouton.Actionnable.ActionType;
import dev.cruding.engine.element.ElementPrinter;
import dev.cruding.engine.entite.Entite;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.Context;
import dev.cruding.engine.gen.Element;

public class Tableau extends Component {
    public String lname;
    public String uname;
    public Actionnable onRowClickAction;
    public Actionnable fillFrom;
    public int largeur = 0;
    public Actionnable actionPagination;
    public Actionnable selection;
    public boolean pagine = false;
    public String sourceDonnee = "liste";

    public Tableau(Element element, Entite entite, Action action, Champ... fieldList) {
        super(element, entite, fieldList);
        this.lname = entite.lname;
        this.uname = entite.uname;
        inElement = true;
        if (action != null) {
            this.actionPagination = new Actionnable(ActionType.NOUI, "changerPage", entite, element).action(action);
            this.pagine = actionPagination != null;
            if (this.actionPagination != null) {
                actionPagination.inElement(inElement);
            }
        }
        sourceDonnee = "liste" + (pagine ? "Paginee" : "") + entite.uname;
        Context.getInstance().addLabel(element.page.module.uname, "aucun." + entite.lname, (entite.setting.feminin ? "Aucune " : "Aucun ") + entite.setting.libelle);


    }

    public Tableau(Element element, Entite entite, Champ... fieldList) {
        this(element, entite, null, fieldList);
    }



    public String getTitle() {
        String key = "liste." + lname;
        addLabel("Uc" + element.page.uc + "." + key, "Liste " + uname);
        return key;
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
            if (c instanceof RefChamp) {
                ((RefChamp) c).addViewScript(flow, element.page.uc, "..");
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
            indent(flow, level + 1).append("<").append(c.ui(ElementPrinter.TABLEAU)).append(" nom=\"").append(prefix).append(c.lname).append("\"");
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

    public Tableau onRowClick(Actionnable actionnable) {
        actionnable.inElement(inElement);
        actionnable.byRow();
        this.onRowClickAction = actionnable;
        return this;
    }

    public Tableau fillFrom(Actionnable actionnable) {
        actionnable.inElement(inElement);
        actionnable.paginee(this.pagine);
        this.fillFrom = actionnable;
        if (this.fillFrom.sourceDonnee != null) {
            sourceDonnee = this.fillFrom.sourceDonnee;
        }
        return this;
    }


    public Tableau largeur(int largeur) {
        this.largeur = largeur;
        return this;
    }

    public Tableau selection(String selection) {
        this.selection = new Actionnable(ActionType.NOUI, selection, entite, element).action(new ActionChangerSelection()).inViewOnly();
        return this;
    }
}
