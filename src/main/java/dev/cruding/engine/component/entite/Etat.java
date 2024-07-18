
package dev.cruding.engine.component.entite;

import org.apache.commons.lang3.StringUtils;
import dev.cruding.engine.action.Action;
import dev.cruding.engine.action.impl.ActionRecupererEnSession;
import dev.cruding.engine.component.ElementComponent;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.element.impl.ElementEtat;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.field.Field;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.Context;
import dev.cruding.engine.gen.Page;
import dev.cruding.engine.gen.Util;

public class Etat extends ElementComponent {

    public int colNumber = 2;
    public int largeur = 0;
    public String lname;
    public String uname;
    public Element element;
    public Field field;
    public boolean inView = true;

    public Etat(Page page, Entity entity, Field... fieldList) {
        this(entity.lname, entity.uname, page, entity, null, fieldList);

    }

    public Etat(Page page, Field field, Entity entity, Field... fieldList) {
        this(field.lname, field.uname, page, entity, field, fieldList);
    }

    private Etat(String lname, String uname, Page page, Entity entity, Field field, Field... fieldList) {
        super(page, entity, fieldList);
        this.lname = lname;
        this.uname = uname;
        this.field = field;
        this.element = new ElementEtat(entity, this);
        page.addElement(element);
        fillFrom(new ActionRecupererEnSession());
    }

    public Etat nom(String uname) {
        this.uname = uname;
        this.lname = StringUtils.uncapitalize(uname);
        return this;
    }

    public Etat inView(boolean inView) {
        this.inView = inView;
        return this;
    }


    public String getTitle() {
        String key = "etat." + lname;
        addLabel(key, "Etat " + uname);
        return key;
    }

    public void addImport(ViewFlow flow) {
        if (inView) {
            if (communModule) {
                flow.addJsImport("Etat" + uname, Util.getRelativePath(page.path, page.moduleDefinition.path, false) + "/commun/Etat" + uname);
            } else if (communEntite) {
                flow.addJsImport("Etat" + uname, "../commun/Etat" + uname);
            } else {
                flow.addJsImport("Etat" + uname, "./element/Etat" + uname);
            }
        }
    }

    public void addOpenTag(ViewFlow flow, int level) {
        if (inView) {
            flow.addToUi(indent[level]).append("<Etat").append(uname).append(" />");
        }

    }

    public void addCloseTag(ViewFlow flow, int level) {}

    public Etat colNumber(int colNumber) {
        this.colNumber = colNumber;
        return this;
    }

    public Etat largeur(int largeur) {
        this.largeur = largeur;
        return this;
    }

    public Etat lname(String lname) {
        this.lname = lname;
        this.uname = StringUtils.capitalize(lname);
        this.element.lname = lname;
        return this;
    }

    public Etat fillFrom(Action action) {

        Context.getInstance().addAction(action, page, element, entity);

        return this;
    }

}
