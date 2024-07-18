package dev.cruding.engine.gen;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import dev.cruding.engine.action.Action;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entity.Entity;

public class Context {
    private final static Context instance = new Context();

    public static final Context getInstance() {
        return instance;
    }

    public static void add(Object instance) {
        if (instance instanceof Page) {
            Page page = (Page) instance;
            getInstance().pageMap.put(page.name, page);
        } else if (instance instanceof Entity) {
            Entity entity = (Entity) instance;
            getInstance().entityMap.put(entity.uname, entity);
        } else if (instance instanceof Module) {
            Module module = (Module) instance;
            getInstance().moduleMap.put(module.packge, module);
        }

    }

    private HashMap<String, Entity> entityMap = new HashMap<>();

    private HashMap<String, Page> pageMap = new HashMap<>();
    private HashMap<String, Module> moduleMap = new HashMap<>();

    private HashMap<String, String> labelMap = new HashMap<>();

    private HashMap<String, String> legacyDbMap = new HashMap<>();
    private ArrayList<Association> associationList = new ArrayList<>();

    private String basePath;
    private int index;

    private Context() {

    }

    public Entity getEntity(String uname) {
        return entityMap.get(uname);
    }

    public Collection<Entity> getEntityList() {
        return entityMap.values();
    }

    public Collection<Module> getModuleList() {
        return moduleMap.values();
    }

    public Page getPage(String name) {
        return pageMap.get(name);
    }

    public Collection<Page> getPageList() {
        return pageMap.values();
    }

    public List<Page> getPageList(Module module) {
        return pageMap.values().stream().filter(page -> page.actionUname != null && page.estReelle() && page.moduleDefinition.uname.equals(module.uname)).toList();
    }

    public void addLabel(String key, String label) {
        labelMap.put(key, label);

    }

    public String getLabel(String key) {
        return labelMap.get(key);
    }

    public HashMap<String, String> getLabelMap() {
        return labelMap;
    }

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public String getLegacyDbName(String uename, String key, String defaultValue) {
        String value = legacyDbMap.get(uename + "." + key);
        return value != null ? value : defaultValue;
    }

    public String getLegacyDbName(String uename, String flname, String key, String defaultValue) {
        String value = legacyDbMap.get(uename + "." + flname + "." + key);
        return value != null ? value : defaultValue;
    }

    public void setLegacyDbMap(HashMap<String, String> legacyDbMap) {
        this.legacyDbMap = legacyDbMap;
    }

    public String addAction(Action action, Page page, Element element, Entity entity) {
        if (action.entity == null && entity != null) {
            action.uc(page.uc).entity(entity);
        } else if (entity == null) {
            System.out.println("action.entity is null");
        }
        action.setInElement(true);
        action.id = "a" + index++;
        associationList.add(new Association(entity, page, element, action));
        return action.id;
    }

    public Action getAction(String id) {
        return associationList.stream().filter(as -> as.action.id.equals(id)).map(as -> as.action).toList().get(0);
    }

    public void addAction(Action action, Page page, Entity entity) {
        action.uc(page.uc);
        action.id = "a" + index++;
        if (entity != null) {
            action.entity(entity);
            associationList.add(new Association(entity, page, action));
        } else {
            associationList.add(new Association(page, action));
        }
    }

    public List<Action> actionPage(Page page) {
        return associationList.stream().filter(as -> as.page.name.equals(page.name)).map(as -> as.action).sorted().toList();
    }

    public List<Action> actionElement(Page page, Element element) {
        return associationList.stream().filter(as -> as.element != null && as.page.name.equals(page.name) && as.element.lname.equals(element.lname)).map(as -> as.action).sorted().toList();
    }

    public List<Action> actionEntity(Entity entity) {
        return associationList.stream().filter(as -> as.entity != null && as.entity.lname.equals(entity.lname)).map(as -> as.action).sorted().toList();
    }

    public Page pageAction(Action action) {
        return associationList.stream().filter(as -> as.action.id.equals(action.id)).map(as -> as.page).toList().get(0);
    }

    public void initEntities() {
        entityMap.values().stream().forEach(e -> e.init());
    }

    public void initPages() {
        pageMap.values().stream().forEach(p -> p.init());
    }

    public Module getModule(String packageName) {
        String key = "";

        for (String str : moduleMap.keySet()) {
            if (packageName.startsWith(str) && str.length() > key.length()) {
                key = str;
            }
        }

        return moduleMap.get(key);
    }

}
