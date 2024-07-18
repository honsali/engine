package dev.cruding.engine.component.entite;

import dev.cruding.engine.action.impl.ActionChercher;
import dev.cruding.engine.action.impl.ActionLocale;
import dev.cruding.engine.component.ElementComponent;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.element.impl.ElementFiltre;
import dev.cruding.engine.element.impl.ElementFiltreInline;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.field.Field;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.Context;
import dev.cruding.engine.gen.Page;

public class Filtre extends ElementComponent {

    public int colNumber = 0;
    public String lname;
    public String uname;

    public Filtre(Page page, Entity entity, boolean inline, Field... fieldList) {
        super(page, entity, fieldList);
        this.lname = entity.lname;
        this.uname = entity.uname;
        Element element = null;
        if (inline) {
            element = new ElementFiltreInline(entity, this);
        } else {
            element = new ElementFiltre(entity, this);
        }

        Context.getInstance().addAction(new ActionLocale("initialiserFiltre"), page, element, entity);
        Context.getInstance().addAction(new ActionLocale("appliquerFiltre"), page, element, entity);
        page.addElement(new ElementFiltreInline(entity, this));
    }

    public String getTitle() {
        String key = "filtre." + lname;
        addLabel(key, "Filtrer " + uname);
        return key;
    }

    public void addImport(ViewFlow flow) {
        flow.addJsImport("Filtre" + uname, "./element/Filtre" + uname);
    }

    public void addOpenTag(ViewFlow flow, int level) {
        flow.addToUi(indent[level]).append("<Filtre").append(entity.uname).append(" />");
    }

    public void addCloseTag(ViewFlow flow, int level) {}

    public void linkToView() {

        Context.getInstance().addAction(new ActionChercher(), page, entity);
    }

    public Filtre colNumber(int colNumber) {
        this.colNumber = colNumber;
        return this;
    }

}
