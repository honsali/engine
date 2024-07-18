
package dev.cruding.engine.component.entite;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.action.impl.ActionInitCreation;
import dev.cruding.engine.action.impl.ActionInitCreationInFormulaire;
import dev.cruding.engine.action.impl.ActionInitModificationDepuisMdl;
import dev.cruding.engine.action.impl.ActionInitModificationInFormulaire;
import dev.cruding.engine.component.ElementComponent;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.element.impl.ElementFormulaire;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.field.Field;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.Context;
import dev.cruding.engine.gen.Page;

public class Formulaire extends ElementComponent {

    public int colNumber = 2;
    public int largeur = 0;
    public boolean enModification = false;
    public String lname;
    public String uname;
    private Element element;
    public Action fillFrom;
    public boolean inView = true;

    public Formulaire(Page page, Entity entity, Field... fieldList) {
        super(page, entity, fieldList);
        this.lname = entity.lname;
        this.uname = entity.uname;
        this.element = new ElementFormulaire(entity, this);
        page.addElement(element);
    }

    public Formulaire nom(String uname) {
        this.uname = uname;
        return this;
    }

    public Formulaire inView(boolean inView) {
        this.inView = inView;
        return this;
    }

    public String getTitle() {
        String key = "formulaire." + lname;
        addLabel(key, "Formulaire " + uname);
        return key;
    }

    public void addImport(ViewFlow flow) {
        if (inView) {
            flow.addJsImport("Formulaire" + uname, "./element/Formulaire" + uname);
        }
    }

    public void addOpenTag(ViewFlow flow, int level) {
        if (inView) {
            flow.useForm(false);
            flow.addToUi(indent[level]).append("<Formulaire").append(uname).append(" form={form} />");
        }
    }

    public void addCloseTag(ViewFlow flow, int level) {}

    public Formulaire colNumber(int colNumber) {
        this.colNumber = colNumber;
        return this;
    }

    public Formulaire largeur(int largeur) {
        this.largeur = largeur;
        return this;
    }

    public Formulaire enModification() {
        this.enModification = true;
        return this;
    }

    public Formulaire fillFrom(Action action) {

        Context.getInstance().addAction(action, page, element, entity);
        this.fillFrom = action;

        return this;
    }

    public Formulaire initModificationDepuidMdl(String mdlName) {
        Context.getInstance().addAction(new ActionInitModificationInFormulaire(this), page, element, entity);
        Context.getInstance().addAction(new ActionInitModificationDepuisMdl(this, mdlName), page, entity);
        enModification();
        return this;
    }

    public Formulaire initModification(Action action) {
        // Context.getInstance().addAction(new ActionInitModificationInFormulaire(this), page, element,
        // entity);
        action.formulaire = this;
        Context.getInstance().addAction(action, page, entity);
        enModification();
        return this;
    }

    public Formulaire initCreation() {
        Context.getInstance().addAction(new ActionInitCreationInFormulaire(this), page, element, entity);
        Context.getInstance().addAction(new ActionInitCreation(this), page, entity);
        return this;
    }
}
