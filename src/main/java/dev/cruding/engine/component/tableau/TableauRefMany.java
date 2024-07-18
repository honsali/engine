package dev.cruding.engine.component.tableau;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.action.ActionAvecTable;
import dev.cruding.engine.action.impl.ActionRecupererEnSession;
import dev.cruding.engine.component.Component;
import dev.cruding.engine.element.impl.ElementTableauRefMany;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.field.Field;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.Context;
import dev.cruding.engine.gen.Page;

public class TableauRefMany extends Component {
    public String lname;
    public String uname;
    private ElementTableauRefMany element;
    public Action onRowClickAction;
    public Action fillFrom;
    public int largeur = 0;
    public Field listField;

    public TableauRefMany(Page page, Entity entity, Field... fieldList) {
        super(page, entity, fieldList);
        this.lname = entity.lname;
        this.uname = entity.uname;
        this.element = new ElementTableauRefMany(entity, this);
        page.addElement(element);
    }

    public String getTitle() {
        String key = "liste." + listField.lname;
        addLabel(key, "Liste " + listField.uname);
        return key;
    }

    public void addImport(ViewFlow flow) {
        flow.addJsImport("Tableau" + listField.uname, "./element/Tableau" + listField.uname);
    }

    public void addOpenTag(ViewFlow flow, int level) {
        flow.addToUi(indent[level]).append("<Tableau").append(listField.uname).append(" />");
    }

    public void addCloseTag(ViewFlow flow, int level) {
    }

    public TableauRefMany onRowClick(Action action) {
        Context.getInstance().addAction(action, page, element, entity);
        this.onRowClickAction = action;
        return this;
    }

    public TableauRefMany fillFrom(ActionAvecTable action) {
        action.setPaginee(false);
        Context.getInstance().addAction(action, page, element, entity);
        this.fillFrom = action;

        return this;
    }

    public TableauRefMany fillFrom(ActionRecupererEnSession action) {
        Context.getInstance().addAction(action, page, element, entity);
        this.fillFrom = action;

        return this;
    }

    public TableauRefMany sur(Field listField) {
        this.listField = listField;
        return this;
    }

    public TableauRefMany largeur(int largeur) {
        this.largeur = largeur;
        return this;
    }
}
