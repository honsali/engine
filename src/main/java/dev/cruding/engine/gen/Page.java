package dev.cruding.engine.gen;

import java.util.ArrayList;
import org.apache.commons.lang3.StringUtils;
import dev.cruding.engine.entite.Entite;
import dev.cruding.engine.gen.helper.Util;

public class Page implements Comparable<Page> {
    public ArrayList<Element> listeElement = new ArrayList<>();

    public String path;
    public String name;
    public String uc;
    public String route;

    public Module module;
    public String actionUname;
    public String actionLname;
    public String entiteUname;
    public String entiteLname;

    public ElementComposer elementComposer;


    public Page(Module module, String name, ElementComposer elementComposer) {
        this.name = name;
        this.uc = name.substring(4);
        int idx1 = Util.findFirstCapitalIndex(name.substring(4));
        if (idx1 > -1) {
            entiteUname = name.substring(idx1 + 4);
            actionUname = name.substring(4, idx1 + 4);
            actionLname = StringUtils.uncapitalize(actionUname);
        } else {
            entiteUname = name.substring(4);
        }
        this.uc = name.substring(4);
        entiteLname = StringUtils.uncapitalize(entiteUname);

        if (module.path.endsWith(entiteLname)) {
            this.path = module.path + "/" + actionLname;
        } else {
            this.path = module.path + "/" + entiteLname + "/" + actionLname;
        }
        this.elementComposer = elementComposer;
        this.elementComposer.setPage(this);
        this.module = module;
    }

    public void init() {
        Element elementRacine = this.elementComposer.compose();
        addElement(elementRacine);
    }

    public int compareTo(Page p) {
        return actionUname.compareTo(p.actionUname) + entiteUname.compareTo(p.entiteUname);
    }

    public Entite getEntite(String uname) {
        return Context.getInstance().getEntite(uname);
    }

    public boolean estReelle() {
        return listeElement.size() > 1 || listeElement.get(0).composantRacine != null;
    }


    public void addElement(Element element) {
        listeElement.add(element);
    }



}
