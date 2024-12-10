
package dev.cruding.engine.gen;

import org.apache.commons.lang3.StringUtils;
import dev.cruding.engine.component.Component;
import dev.cruding.engine.flow.ViewFlow;

public class Element {

    public String path;
    public String relativePath;
    public String name;
    public Page page;
    public Component composantRacine;
    public boolean byForm = false;
    public String byProp = null;


    public Element(String name, String relativePath) {
        this.name = name;
        this.relativePath = relativePath;
    }

    public Element name(String name) {
        this.name = name;
        return this;
    }

    public Element page(Page page) {
        if (relativePath.equals("")) {
            System.out.println("ok");
        }
        if (StringUtils.startsWith(relativePath, "../")) {
            this.path = StringUtils.substringBeforeLast(page.path, "/") + "/" + StringUtils.substringAfter(relativePath, "/");
        } else if (page == null) {
            this.path = relativePath;
        } else {
            this.path = page.path + relativePath;
        }
        this.page = page;
        return this;
    }

    public Element composantRacine(Component component) {
        this.composantRacine = component;
        return this;
    }

    public Element byForm() {
        this.byForm = true;
        return this;
    }

    public Element byProp(String byProp) {
        this.byProp = byProp;
        return this;
    }

    public void addContent(ViewFlow flow) {
        if (composantRacine == null) {
            return;
        }
        composantRacine.addContent(null, flow, 1);
    }

}
