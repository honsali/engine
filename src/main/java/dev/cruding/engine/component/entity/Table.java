package dev.cruding.engine.component.entity;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.component.Component;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.field.Field;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.Context;

public class Table extends Component {

    public Action onRowClickAction;
    public Action fillFrom;
    public int width = 0;
    public Action paginationAction;
    public boolean selection;
    public boolean paginated = false;
    public String dataSource = "liste";


    public Table(Element element, Entity entity, Field... fieldList) {
        super(element, entity, fieldList);
        inElement = true;
        Context.getInstance().addLabel(element.page.module.uname, "aucun." + entity.lname, (entity.setting.feminine ? "Aucune " : "Aucun ") + entity.setting.label);

    }

    public Table onRowClick(Action action) {
        action.inElement(inElement);
        action.byRow();
        this.onRowClickAction = action;
        return this;
    }

    public Table fillWith(Action action) {
        if (action.paginationAction != null) {
            this.paginated = true;
            action.paginationAction.element(this.element);
        }

        dataSource = action.nameVariable != null ? action.nameVariable : "liste" + (paginated ? "Paginee" : "") + entity.uname;
        action.inElement(inElement);
        action.asList();
        this.fillFrom = action;
        if (this.fillFrom.dataSource != null) {
            dataSource = this.fillFrom.dataSource;
        }
        return this;
    }

    public Table selection() {
        this.selection = true;
        return this;
    }

    public Table width(int width) {
        this.width = width;
        return this;
    }

    public void addImport(ViewFlow flow) {
        flow.addJsImport("{ Colonne, Tableau }", "waxant");
        flow.addSelector(dataSource);
    }

    public void addScript(ViewFlow flow) {
        if (fieldList.length > 0) {
            flow.totalScript().L();
        }
        for (int i = 0; i < fieldList.length; i++) {
            Field c = fieldList[i];
            boolean retourLigne = c.addViewScript(flow, element.page.uc, "..");
            if (retourLigne && i < fieldList.length - 1) {
                flow.totalScript().L();
            }
        }
    }

    public boolean addOpenTag(ViewFlow flow, int level) {
        if (paginated) {
            indent(flow, level).append("<Tableau listeDonnee={").append(dataSource).append(".liste}");
            flow.totalUi().__(" pagination={").append(dataSource).append(".pagination}");
        } else {
            indent(flow, level).append("<Tableau listeDonnee={").append(dataSource).append("}");
        }
        if (onRowClickAction != null) {
            flow.totalUi().__(" siClicLigne={").append(onRowClickAction.lnameWithEntity).append("}");
        }
        if (paginated) {
            flow.totalUi().__(" siChangementPage={actionChangementPage}");
        }
        if (selection) {
            flow.totalUi().__(" siSelectionChange={changerSelection}");
        }
        flow.totalUi().__(" texteAucunResultat=\"").append("aucun.").append(entity.lname).append("\"");
        flow.totalUi().__(">");

        for (Field c : fieldList) {
            String prefix = c.of == null ? "" : c.of.lname + ".";
            indent(flow, level + 1).append("<").append(c.ui(Element.TABLE)).append(" nom=\"").append(prefix).append(c.lname).append("\"");
            if (c.label != null) {
                flow.totalUi().__(" libelle=\"").append(c.label).append("\"");
            }
            if (c.width > 0) {
                flow.totalUi().__(" width={", "" + c.width, "}");
            }
            flow.totalUi().__(" />");
            Context.getInstance().addLabelForField(element.page.module.uname, c);
        }
        return false;
    }

    public void addCloseTag(ViewFlow flow, int level) {
        indent(flow, level).append("</Tableau>");
    }
}
