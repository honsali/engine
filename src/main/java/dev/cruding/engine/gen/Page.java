package dev.cruding.engine.gen;

import java.util.ArrayList;
import java.util.Comparator;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Strings;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entity.Entity;

public class Page {
    public static final Comparator<Page> ORDER_BY_ACTION_AND_ENTITY = Page::compareByActionAndEntity;

    private static int compareByActionAndEntity(Page left, Page right) {
        if (left == right) {
            return 0;
        }
        if (left == null) {
            return -1;
        }
        if (right == null) {
            return 1;
        }
        int actionComparison = Strings.CS.compare(left.actionUname, right.actionUname);
        if (actionComparison != 0) {
            return actionComparison;
        }
        int entityComparison = Strings.CS.compare(left.entityUname, right.entityUname);
        if (entityComparison != 0) {
            return entityComparison;
        }
        return Strings.CS.compare(left.name, right.name);
    }

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

    public ViewComposer<?> elementComposer;

    public Page(Module module, ViewComposer<?> elementComposer) {
        if (module == null) {
            throw new ContextException("Page module cannot be null");
        }
        if (elementComposer == null) {
            throw new ContextException("Page element composer cannot be null");
        }
        this.module = module;
        this.elementComposer = elementComposer;

        Class<? extends Entity> entityType = elementComposer.entityType();
        entityUname = entityType.getSimpleName();
        entityLname = StringUtils.uncapitalize(entityUname);

        String viewName = elementComposer.getClass().getSimpleName();
        if (!viewName.startsWith("View")) {
            throw new ContextException("Page view class name must start with 'View': " + viewName);
        }
        this.uc = viewName.substring(4);
        if (!this.uc.endsWith(entityUname)) {
            throw new ContextException("Page view class name must end with entity name '" + entityUname + "': " + viewName);
        }
        actionUname = this.uc.substring(0, this.uc.length() - entityUname.length());
        if (StringUtils.isBlank(actionUname)) {
            throw new ContextException("Page view class name must contain a page name before entity name: " + viewName);
        }
        actionLname = StringUtils.uncapitalize(actionUname);
        this.name = "Page" + this.uc;

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
        this.elementComposer.addElement();
    }


    public boolean containsComponent() {
        return elementList.size() > 0 && elementList.get(0).rootComponent != null;
    }

    public void addElement(Element element) {
        if (!elementList.contains(element)) {
            elementList.add(element);
        }
    }

    public Page icon(String icon) {
        this.icon = icon;
        this.module.pageIndex = this;
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
