
package dev.cruding.engine.component.entite;

import dev.cruding.engine.component.Component;
import dev.cruding.engine.component.ElementComponent;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.element.impl.ElementPanneau;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.field.Field;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.Page;
import dev.cruding.engine.gen.Util;

public class Panneau extends ElementComponent {

    public int colNumber = 2;
    public int largeur = 0;
    public String lname;
    public String uname;
    public Element element;
    public Field field;
    public boolean communModule = false;

    public Panneau(Page page, Entity entity, Component... componentList) {
        super(page, entity, componentList);
        this.lname = entity.lname;
        this.uname = entity.uname;
        this.field = null;
        this.element = new ElementPanneau(entity, this);
        page.addElement(element);
    }

    public Panneau communModule() {
        this.communModule = true;
        return this;
    }

    public String getTitle() {
        String key = "panneau." + lname;
        addLabel(key, "Panneau " + uname);
        return key;
    }

    public void addImport(ViewFlow flow) {
        if (communModule) {
            flow.addJsImport("Panneau" + uname, Util.getRelativePath(page.path, page.moduleDefinition.path, false) + "/commun/Panneau" + uname);
        } else {
            flow.addJsImport("Panneau" + uname, "./element/Panneau" + uname);
        }
    }

    public void addOpenTag(ViewFlow flow, int level) {
        flow.addToUi(indent[level]).append("<Panneau").append(uname).append(" />");
    }

    public void addCloseTag(ViewFlow flow, int level) {}

    public void addContent(Component fatherComponent, ViewFlow flow, int level) {
        this.fatherComponent = fatherComponent;
        addImport(flow);
        addScript(flow);
        addOpenTag(flow, level);

        addCloseTag(flow, level);
        if (level == 1) {
            flow.flush(this);
        }
    }

}
