package dev.cruding.engine.gen;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import org.apache.commons.lang3.StringUtils;
import dev.cruding.engine.action.Action;
import dev.cruding.engine.champ.Champ;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entite.Entite;

public class Contexte {


    private final static Contexte instance = new Contexte();

    public static final Contexte getInstance() {
        return instance;
    }

    private HashMap<String, Entite> entiteMap = new HashMap<>();
    private HashMap<String, Page> pageMap = new HashMap<>();
    private HashMap<String, Module> moduleMap = new HashMap<>();
    private HashMap<String, HashMap<String, String>> labelMap = new HashMap<>();
    private HashMap<String, String> legacyDbMap = new HashMap<>();
    private HashSet<Action> actionList = new HashSet<>();

    private String basePath;

    private Contexte() {}

    public <T> void add(T instance) {
        if (instance instanceof Page page) {
            if (StringUtils.isBlank(page.name)) {
                throw new ContexteException("Cannot add Page with null or empty name");
            }
            getInstance().pageMap.put(page.name, page);
        } else if (instance instanceof Entite entite) {
            if (StringUtils.isBlank(entite.uname)) {
                throw new ContexteException("Cannot add Entite with null or empty uname");
            }
            getInstance().entiteMap.put(entite.uname, entite);
        } else if (instance instanceof Module module) {
            if (StringUtils.isBlank(module.packge)) {
                throw new ContexteException("Cannot add Module with null or empty package");
            }
            getInstance().moduleMap.put(module.packge, module);
        } else {
            throw new ContexteException(String.format("Unknown object type: %s. Expected Page, Entite, or Module.", instance != null ? instance.getClass().getSimpleName() : "null"));
        }
    }

    public void addPage(Page page) {
        if (page == null || StringUtils.isBlank(page.name)) {
            throw new ContexteException("Cannot add null page or page with empty name");
        }
        pageMap.put(page.name, page);
    }

    public Entite getEntite(String uname) {
        if (StringUtils.isBlank(uname)) {
            throw new ContexteException("Entity name cannot be null or empty");
        }
        Entite entite = entiteMap.get(uname);
        if (entite == null) {
            throw new ContexteException(String.format("Entity '%s' not found", uname));
        }
        return entite;
    }

    public Collection<Entite> getEntiteList() {
        return entiteMap.values();
    }

    public Collection<Module> getModuleList() {
        return moduleMap.values();
    }

    public Page getPage(String name) {
        if (StringUtils.isBlank(name)) {
            throw new ContexteException("Page name cannot be null or empty");
        }
        Page page = pageMap.get(name);
        if (page == null) {
            throw new ContexteException(String.format("Page '%s' not found", name));
        }
        return page;
    }

    public Collection<Page> getPageList() {
        return pageMap.values();
    }

    public List<Page> getPageList(Module module) {
        if (module == null) {
            throw new ContexteException("Module cannot be null");
        }
        if (StringUtils.isBlank(module.uname)) {
            throw new ContexteException("Module uname cannot be null or empty");
        }

        return pageMap.values().stream().filter(page -> page.actionUname != null && page.module != null && page.module.uname != null && page.module.uname.equals(module.uname)).toList();
    }

    public void addLabelPourChamp(String module, Champ c) {
        if (StringUtils.isBlank(module)) {
            throw new ContexteException("Module name cannot be null or empty");
        }
        if (c == null || StringUtils.isBlank(c.lname)) {
            throw new ContexteException("Champ or champ.lname cannot be null or empty");
        }

        if (c.lname.startsWith("code") || c.lname.startsWith("id") || c.lname.startsWith("libelle")) {
            return;
        }
        addLabel(module, c.lname, StringUtils.capitalize(StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(c.lname), " ")));
    }

    public void addLabel(String module, String key, String label) {
        if (StringUtils.isBlank(module)) {
            throw new ContexteException("Module name cannot be null or empty");
        }
        if (StringUtils.isBlank(key)) {
            throw new ContexteException("Label key cannot be null or empty");
        }
        if (StringUtils.isBlank(label)) {
            throw new ContexteException("Label value cannot be null or empty");
        }

        String newKey = key.indexOf(".") > 0 ? "'" + key + "'" : key;
        labelMap.computeIfAbsent(module, k -> new HashMap<>()).put(newKey, label);
    }

    public HashMap<String, String> getLabelMap(String module) {
        if (StringUtils.isBlank(module)) {
            throw new ContexteException("Module name cannot be null or empty");
        }
        return labelMap.get(module); // Can return null - that's OK for labels
    }

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        if (StringUtils.isBlank(basePath)) {
            throw new ContexteException("Base path cannot be null or empty");
        }
        this.basePath = basePath;
    }

    public String getLegacyDbName(String uename, String key, String defaultValue) {
        if (StringUtils.isBlank(uename)) {
            throw new ContexteException("Entity name cannot be null or empty");
        }
        if (StringUtils.isBlank(key)) {
            throw new ContexteException("Key cannot be null or empty");
        }
        String value = legacyDbMap.get(uename + "." + key);
        return value != null ? value : defaultValue;
    }

    public String getLegacyDbName(String uename, String flname, String key, String defaultValue) {
        if (StringUtils.isBlank(uename)) {
            throw new ContexteException("Entity name cannot be null or empty");
        }
        if (StringUtils.isBlank(flname)) {
            throw new ContexteException("Field name cannot be null or empty");
        }
        if (StringUtils.isBlank(key)) {
            throw new ContexteException("Key cannot be null or empty");
        }
        String value = legacyDbMap.get(uename + "." + flname + "." + key);
        return value != null ? value : defaultValue;
    }

    public void setLegacyDbMap(HashMap<String, String> legacyDbMap) {
        this.legacyDbMap = legacyDbMap != null ? legacyDbMap : new HashMap<>();
    }

    public void addAction(Action action) {
        if (action == null) {
            throw new ContexteException("Action cannot be null");
        }
        actionList.add(action);
    }

    public List<Action> actionPage(Page page) {
        if (page == null || StringUtils.isBlank(page.name)) {
            throw new ContexteException("Page cannot be null and must have a name");
        }
        return actionList.stream().filter(as -> as.page != null && as.page.name != null && as.page.name.equals(page.name)).filter(Objects::nonNull).sorted().toList();
    }

    public List<Action> actionElement(Element element) {
        if (element == null) {
            throw new ContexteException("Element cannot be null");
        }
        return actionList.stream().filter(as -> as.element != null && as.element.equals(element)).filter(Objects::nonNull).sorted().toList();
    }

    public List<Action> actionEntite(Entite entite) {
        if (entite == null || StringUtils.isBlank(entite.lname)) {
            throw new ContexteException("Entite cannot be null and must have an lname");
        }
        return actionList.stream().filter(as -> as.entite != null && as.entite.lname != null && as.entite.lname.equals(entite.lname)).filter(Objects::nonNull).distinct().sorted().toList();
    }

    public void initEntities() {
        entiteMap.values().stream().forEach(e -> e.init());
    }

    public void initPages() {
        pageMap.values().stream().forEach(p -> p.init());
    }

    public void initActions() {
        actionList.stream().forEach(a -> a.init());
    }

    public Module getModule(String packageName) {
        if (StringUtils.isBlank(packageName)) {
            throw new ContexteException("Package name cannot be null or empty");
        }

        String bestMatch = "";
        for (String str : moduleMap.keySet()) {
            if (packageName.startsWith(str) && str.length() > bestMatch.length()) {
                bestMatch = str;
            }
        }

        if (bestMatch.isEmpty()) {
            throw new ContexteException(String.format("No module found for package: %s", packageName));
        }

        return moduleMap.get(bestMatch);
    }
}
