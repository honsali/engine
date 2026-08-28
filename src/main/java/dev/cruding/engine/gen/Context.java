package dev.cruding.engine.gen;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import org.apache.commons.lang3.StringUtils;
import dev.cruding.engine.action.Action;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.field.Field;

public class Context {


    private final static Context instance = new Context();

    public static final Context getInstance() {
        return instance;
    }

    private HashMap<String, Entity> entityMapByName = new HashMap<>();
    private HashMap<Class<? extends Entity>, Entity> entityMapByClass = new HashMap<>();
    private HashMap<String, Page> pageMap = new HashMap<>();
    private HashMap<String, Module> moduleMap = new HashMap<>();
    private HashMap<String, HashMap<String, String>> labelMap = new HashMap<>();
    private HashSet<Action> actionList = new HashSet<>();


    private String basePath;



    private Context() {}

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        if (StringUtils.isBlank(basePath)) {
            throw new ContextException("Base path cannot be null or empty");
        }
        this.basePath = basePath;
    }

    /* ****************************************************************************** */
    /* ********************************** ENTITIES ********************************** */
    /* ****************************************************************************** */
    public void addEntity(Entity entity) {
        if (entity == null || StringUtils.isBlank(entity.uname)) {
            throw new ContextException("Cannot add null Entity or Entity with null or empty uname");
        }
        if (entityMapByName.containsKey(entity.uname)) {
            throw new ContextException("Doublon Entity: " + entity.uname);
        }
        if (entityMapByClass.containsKey(entity.getClass())) {
            throw new ContextException("Doublon Entity class: " + entity.getClass().getName());
        }
        entityMapByName.put(entity.uname, entity);
        entityMapByClass.put(entity.getClass(), entity);
    }

    public void initEntities() {
        entityMapByName.values().stream().forEach(e -> e.init());
    }

    public Collection<Entity> getEntityList() {
        return entityMapByName.values();
    }

    public Entity getEntity(String uname) {
        if (StringUtils.isBlank(uname)) {
            throw new ContextException("Entity name cannot be null or empty");
        }
        Entity entity = entityMapByName.get(uname);
        if (entity == null) {
            throw new ContextException(String.format("Entity '%s' not found", uname));
        }
        return entity;
    }

    public <T extends Entity> T getEntity(Class<T> entityType) {
        if (entityType == null) {
            throw new ContextException("Entity type cannot be null");
        }
        Entity entity = entityMapByClass.get(entityType);
        if (entity == null) {
            throw new ContextException(String.format("Entity '%s' not found", entityType.getSimpleName()));
        }
        return entityType.cast(entity);
    }

    /* ****************************************************************************** */
    /* ********************************** MODULES ********************************** */
    /* ****************************************************************************** */
    public void addModule(Module module) {
        if (module == null || StringUtils.isBlank(module.packge)) {
            throw new ContextException("Cannot add Module with null or empty package");
        }
        if (moduleMap.containsKey(module.packge)) {
            throw new ContextException("Doublon Module: " + module.packge);
        }
        moduleMap.put(module.packge, module);
    }

    public Collection<Module> getModuleList() {
        return moduleMap.values();
    }

    /* ****************************************************************************** */
    /* ********************************** PAGES ********************************** */
    /* ****************************************************************************** */

    public void addPage(Page page) {
        if (page == null || StringUtils.isBlank(page.name)) {
            throw new ContextException("Cannot add null page or page with empty name");
        }
        if (pageMap.containsKey(page.name)) {
            throw new ContextException("Doublon Page: " + page.name);
        }
        pageMap.put(page.name, page);
    }

    public void initPages() {
        pageMap.values().stream().forEach(p -> p.init());
    }

    public Collection<Page> getPageList() {
        return pageMap.values();
    }

    public List<Page> getPageList(Module module) {
        if (module == null) {
            throw new ContextException("Module cannot be null");
        }
        if (StringUtils.isBlank(module.uname)) {
            throw new ContextException("Module uname cannot be null or empty");
        }

        return pageMap.values().stream().filter(page -> page.module != null && page.module.uname != null && page.module.uname.equals(module.uname)).toList();
    }

    public void addLabelForField(String module, Field c) {
        if (StringUtils.isBlank(module)) {
            throw new ContextException("Module name cannot be null or empty");
        }
        if (c == null || StringUtils.isBlank(c.lname)) {
            throw new ContextException("Field or field lname cannot be null or empty");
        }

        if (c.lname.startsWith("code") || c.lname.startsWith("id") || c.lname.startsWith("libelle")) {
            return;
        }
        addLabel(module, c.lname, StringUtils.capitalize(StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(c.lname), " ")));
    }

    public void addLabel(String module, String key, String label) {
        if (StringUtils.isBlank(module)) {
            throw new ContextException("Module name cannot be null or empty");
        }
        if (StringUtils.isBlank(key)) {
            throw new ContextException("Label key cannot be null or empty");
        }
        if (StringUtils.isBlank(label)) {
            throw new ContextException("Label value cannot be null or empty");
        }

        labelMap.computeIfAbsent(module, k -> new HashMap<>()).put(key, label);
    }

    public HashMap<String, String> getLabelMap(String module) {
        if (StringUtils.isBlank(module)) {
            throw new ContextException("Module name cannot be null or empty");
        }
        return labelMap.get(module);
    }



    /* ****************************************************************************** */
    /* ********************************** ACTIONS ********************************** */
    /* ****************************************************************************** */

    public void addAction(Action action) {
        if (action == null) {
            throw new ContextException("Action cannot be null");
        }
        actionList.add(action);
    }

    public void initActions() {
        actionList.stream().forEach(a -> a.init());
    }

    public List<Action> actionPage(Page page) {
        if (page == null || StringUtils.isBlank(page.name)) {
            throw new ContextException("Page cannot be null and must have a name");
        }
        return actionList.stream().filter(as -> as.page != null && as.page.name != null && as.page.name.equals(page.name)).filter(Objects::nonNull).sorted(Action.ORDER_BY_NAME).toList();
    }

    public List<Action> actionElement(Element element) {
        if (element == null) {
            throw new ContextException("Element cannot be null");
        }
        return actionList.stream().filter(as -> as.element != null && as.element.equals(element)).filter(Objects::nonNull).sorted(Action.ORDER_BY_NAME).toList();
    }

    public List<Action> actionEntity(Entity entity) {
        if (entity == null || StringUtils.isBlank(entity.lname)) {
            throw new ContextException("Entity cannot be null and must have an lname");
        }
        return actionList.stream().filter(as -> as.entity != null && as.entity.lname != null && as.entity.lname.equals(entity.lname)).filter(Objects::nonNull).sorted(Action.ORDER_BY_NAME).toList();
    }
}
