package dev.cruding.engine.gen;

import org.apache.commons.lang3.StringUtils;
import dev.cruding.engine.gen.helper.Util;

public class Module {

    public String id;
    public String uname;
    public String unameLast;
    public String lnameLast;
    public String path;
    public String packge;
    public String listePage;
    public String pageIndex;
    public boolean estParent;
    public boolean estMenuOnglet;
    public String icone;
    public int dernierePostion = 0;

    public Module() {
        uname = this.getClass().getSimpleName();
        packge = StringUtils.substringAfter(this.getClass().getPackageName(), "modules.");
        this.path = "modules/" + packge.replace('.', '/');
        this.unameLast = uname.substring(6);
        this.lnameLast = StringUtils.uncapitalize(this.unameLast);
        this.listePage = "ListePage" + unameLast;
    }

    public String listePage(String path_, boolean inElement) {
        String rp = Util.getRelativePath(path_, path, inElement);
        return rp + "/" + listePage;
    }

    public String listePage(String path_) {
        return listePage(path_, false);
    }

    public Page addPage(String name, ElementComposer elementComposer) {
        Page page = new Page(this, name, elementComposer);
        page.position = dernierePostion++;
        Contexte.getInstance().addPage(page);
        return page;
    }

    public Module menuIcone(String icone) {
        this.icone = icone;
        return this;
    }
}
