
package dev.cruding.engine.element;

import org.apache.commons.lang3.StringUtils;
import dev.cruding.engine.composant.Composant;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.Page;

public class Element {

    public static final String DETAIL = "DETAIL";
    public static final String FORM = "FORM";
    public static final String TABLEAU = "TABLEAU";

    public String path;
    public String relativePath;
    public String name;
    public Page page;
    public Composant composantRacine;
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

    public void setComposantRacine(Composant composant) {
        this.composantRacine = composant;
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

    public boolean equals(Object obj) {
        if (obj instanceof Element) {
            Element e = (Element) obj;
            return e.name.equals(name) && e.path.equals(path);
        }
        return false;
    }

}
