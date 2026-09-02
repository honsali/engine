package dev.cruding.engine.gen;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
import dev.cruding.engine.action.Action;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.field.Field;

public class Context {

    private final Map<String, Entity> entityMapByName = new LinkedHashMap<>();
    private final Map<Class<? extends Entity>, Entity> entityMapByClass = new LinkedHashMap<>();
    private final Map<String, Page> pageMap = new LinkedHashMap<>();
    private final Map<String, Module> moduleMap = new LinkedHashMap<>();
    private final Map<String, Map<String, String>> labelMap = new LinkedHashMap<>();
    private final Set<Action> actionList = new LinkedHashSet<>();
    private final String basePath;
    private final DbNameMapper dbNameMapper;
    private int actionRank;

    public Context(String basePath) {
        this(basePath, new DbNameMapper());
    }

    public Context(String basePath, DbNameMapper dbNameMapper) {
        if (StringUtils.isBlank(basePath)) {
            throw new ContextException("Base path cannot be null or empty");
        }
        this.basePath = basePath;
        this.dbNameMapper = Objects.requireNonNull(dbNameMapper, "DbNameMapper cannot be null");
    }

    public String getBasePath() {
        return basePath;
    }

    public DbNameMapper getDbNameMapper() {
        return dbNameMapper;
    }

    public String nextActionId() {
        return Integer.toString(actionRank++);
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
        entity.attachTo(this);
        entityMapByName.put(entity.uname, entity);
        entityMapByClass.put(entity.getClass(), entity);
    }

    public void initEntities() {
        entityMapByName.values().stream().forEach(e -> e.init());
    }

    public Collection<Entity> getEntityList() {
        return List.copyOf(entityMapByName.values());
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
        if (module.context() != this) {
            throw new ContextException("Module belongs to another Context: " + module.uname);
        }
        if (moduleMap.containsKey(module.packge)) {
            throw new ContextException("Doublon Module: " + module.packge);
        }
        moduleMap.put(module.packge, module);
    }

    public Collection<Module> getModuleList() {
        return List.copyOf(moduleMap.values());
    }

    /* ****************************************************************************** */
    /* ********************************** PAGES ********************************** */
    /* ****************************************************************************** */

    public void addPage(Page page) {
        if (page == null || StringUtils.isBlank(page.name)) {
            throw new ContextException("Cannot add null page or page with empty name");
        }
        if (page.context() != this) {
            throw new ContextException("Page belongs to another Context: " + page.name);
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
        return List.copyOf(pageMap.values());
    }

    public Page getPage(String name) {
        if (StringUtils.isBlank(name)) {
            throw new ContextException("Page name cannot be null or empty");
        }
        Page page = pageMap.get(name);
        if (page == null) {
            throw new ContextException(String.format("Page '%s' not found", name));
        }
        return page;
    }

    public Page getPage(PageRef reference) {
        return getPage(Objects.requireNonNull(reference, "Page reference cannot be null").name());
    }

    public List<Page> getPageList(Module module) {
        if (module == null) {
            throw new ContextException("Module cannot be null");
        }
        if (module.context() != this) {
            throw new ContextException("Module belongs to another Context: " + module.uname);
        }
        if (StringUtils.isBlank(module.uname)) {
            throw new ContextException("Module uname cannot be null or empty");
        }

        return pageMap.values().stream().filter(page -> page.module == module).toList();
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

        labelMap.computeIfAbsent(module, k -> new LinkedHashMap<>()).put(key, label);
    }

    public Map<String, String> getLabelMap(String module) {
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
        if (action.context() != this) {
            throw new ContextException("Action belongs to another Context: " + action.lnameWithEntity);
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
        if (page.context() != this) {
            throw new ContextException("Page belongs to another Context: " + page.name);
        }
        return actionList.stream().filter(action -> action.page == page).sorted(Action.ORDER_BY_NAME).toList();
    }

    public List<Action> actionElement(Element element) {
        if (element == null) {
            throw new ContextException("Element cannot be null");
        }
        if (element.context() != this) {
            throw new ContextException("Element belongs to another Context: " + element.name);
        }
        return actionList.stream().filter(action -> action.element == element).sorted(Action.ORDER_BY_NAME).toList();
    }

    public List<Action> actionEntity(Entity entity) {
        if (entity == null || StringUtils.isBlank(entity.lname)) {
            throw new ContextException("Entity cannot be null and must have an lname");
        }
        if (entity.context() != this) {
            throw new ContextException("Entity belongs to another Context: " + entity.uname);
        }
        return actionList.stream().filter(action -> action.entity == entity).sorted(Action.ORDER_BY_NAME).toList();
    }
}
