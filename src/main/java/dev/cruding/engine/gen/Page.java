package dev.cruding.engine.gen;

import java.util.ArrayList;
import org.apache.commons.lang3.StringUtils;
import dev.cruding.engine.element.Element;

public class Page implements Comparable<Page> {
    public ArrayList<Element> elementList = new ArrayList<>();

    public String name;
    public String path;
    public String uc;
    public String icon;
    public int position;// under tab menu module

    public Module module;
    public String actionUname;
    public String actionLname;
    public String entityUname;
    public String entityLname;

    public boolean pathById = false;

    public ElementComposer elementComposer;

    public Page(Module module, String name, ElementComposer elementComposer) {
        this.module = module;
        this.name = name;
        this.elementComposer = elementComposer;

        this.uc = name.substring(4);
        entityUname = this.uc;

        int idx1 = Util.findFirstCapitalIndex(this.uc);
        if (idx1 > -1) {
            entityUname = name.substring(idx1 + 4);
            actionUname = name.substring(4, idx1 + 4);
            actionLname = StringUtils.uncapitalize(actionUname);
        }
        entityLname = StringUtils.uncapitalize(entityUname);

        if (module.path.endsWith(entityLname)) {
            this.path = module.path + "/" + actionLname;
        } else if (module.path.endsWith(actionLname)) {
            this.path = module.path + "/" + entityLname;
        } else {
            this.path = module.path + "/" + entityLname + "/" + actionLname;
        }
        this.elementComposer.setPage(this);

    }

    public void init() {
        Element rootElement = this.elementComposer.addElement();
        addElement(rootElement);
    }

    public int compareTo(Page p) {
        return actionUname.compareTo(p.actionUname) + entityUname.compareTo(p.entityUname);
    }


    public boolean containsComponent() {
        return elementList.size() > 0 && elementList.get(0).rootComponent != null;
    }

    public void addElement(Element element) {
        elementList.add(element);
    }

    public Page icon(String icon) {
        this.icon = icon;
        this.module.pageIndex = this.name;
        return this;
    }

    public Page pathById() {
        this.pathById = true;
        return this;
    }


    public int getPosition() {
        return position;
    }

}
