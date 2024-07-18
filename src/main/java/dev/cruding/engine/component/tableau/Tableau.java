package dev.cruding.engine.component.tableau;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.action.ActionAvecTable;
import dev.cruding.engine.action.impl.ActionRecupererEnSession;
import dev.cruding.engine.component.Component;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.element.impl.ElementTableau;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.field.Field;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.Context;
import dev.cruding.engine.gen.Page;

public class Tableau extends Component {
    public String lname;
    public String uname;
    public Element element;
    public Action onRowClickAction;
    public Action fillFrom;
    public int largeur = 0;
    public Action actionPagination;
    public boolean pagine = false;

    public Tableau(Page page, Entity entity, ActionAvecTable actionPagination, Field... fieldList) {
        super(page, entity, fieldList);
        this.lname = entity.lname;
        this.uname = entity.uname;
        this.actionPagination = actionPagination;
        this.pagine = actionPagination != null;
        this.element = new ElementTableau(entity, this);
        page.addElement(element);
        if (this.actionPagination != null) {
            Context.getInstance().addAction(actionPagination, page, element, entity);
        }
    }

    public Tableau(Page page, Entity entity, Field... fieldList) {
        this(page, entity, null, fieldList);
    }

    public Tableau byGrandFatherId() {
        if (this.actionPagination != null) {
            this.actionPagination.byGrandFatherId();
        }
        if (this.fillFrom != null) {
            this.fillFrom.byGrandFatherId();
        }
        return this;
    }

    public String getTitle() {
        String key = "liste." + lname;
        addLabel(key, "Liste " + uname);
        return key;
    }

    public void addImport(ViewFlow flow) {
        if (fatherComponent.inElement) {
            flow.addJsImport("Tableau" + uname, "./Tableau" + uname);
        } else {
            flow.addJsImport("Tableau" + uname, "./element/Tableau" + uname);
        }
    }

    public void addOpenTag(ViewFlow flow, int level) {
        flow.addToUi(indent[level]).append("<Tableau").append(entity.uname).append(" />");
    }

    public void addCloseTag(ViewFlow flow, int level) {}

    public Tableau onRowClick(Action action) {
        Context.getInstance().addAction(action, page, element, entity);
        this.onRowClickAction = action;
        return this;
    }

    public Tableau fillFrom(ActionAvecTable action) {
        action.setPaginee(this.pagine);
        Context.getInstance().addAction(action, page, element, entity);
        this.fillFrom = action;
        return this;
    }

    public Tableau fillFrom(ActionRecupererEnSession action) {
        Context.getInstance().addAction(action, page, element, entity);
        this.fillFrom = action;

        return this;
    }

    public Tableau largeur(int largeur) {
        this.largeur = largeur;
        return this;
    }
}
