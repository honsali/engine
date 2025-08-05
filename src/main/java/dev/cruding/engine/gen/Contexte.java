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

    private Contexte() {
    }

    public <T> void add(T instance) {
        if (instance instanceof Page page) {
            getInstance().pageMap.put(page.name, page);
        } else if (instance instanceof Entite entite) {
            getInstance().entiteMap.put(entite.uname, entite);
        } else if (instance instanceof Module module) {
            getInstance().moduleMap.put(module.packge, module);
        }
    }

    public void addPage(Page page) {
        pageMap.put(page.name, page);
    }

    public Entite getEntite(String uname) {
        return entiteMap.get(uname);
    }

    public Collection<Entite> getEntiteList() {
        return entiteMap.values();
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
        return pageMap.values().stream().filter(page -> page.actionUname != null && page.module.uname.equals(module.uname)).toList();
    }

    public void addLabelPourChamp(String module, Champ c) {
        if (c.lname.startsWith("code") || c.lname.startsWith("id") || c.lname.startsWith("libelle")) {
            return;
        }
        addLabel(module, c.lname, StringUtils.capitalize(StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(c.lname), " ")));
    }

    public void addLabel(String module, String key, String label) {
        String newKey = key.indexOf(".") > 0 ? "'" + key + "'" : key;
        labelMap.computeIfAbsent(module, k -> new HashMap<>()).put(newKey, label);
    }

    public HashMap<String, String> getLabelMap(String module) {
        return labelMap.get(module);
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

    public void addAction(Action action) {
        actionList.add(action);
    }

    public List<Action> actionPage(Page page) {
        return actionList.stream().filter(as -> as.page != null && as.page.name.equals(page.name)).filter(Objects::nonNull).sorted().toList();
    }

    public List<Action> actionElement(Element element) {
        return actionList.stream().filter(as -> as.element.equals(element)).filter(Objects::nonNull).sorted().toList();
    }

    public List<Action> actionEntite(Entite entite) {
        return actionList.stream().filter(as -> as.entite != null && as.entite.lname.equals(entite.lname)).filter(Objects::nonNull).distinct().sorted().toList();
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
        String key = "";

        for (String str : moduleMap.keySet()) {
            if (packageName.startsWith(str) && str.length() > key.length()) {
                key = str;
            }
        }

        return moduleMap.get(key);
    }
}
