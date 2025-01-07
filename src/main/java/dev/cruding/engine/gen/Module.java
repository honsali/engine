package dev.cruding.engine.gen;

import org.apache.commons.lang3.StringUtils;
import dev.cruding.engine.gen.helper.Util;

public class Module {

    public String id;
    public String uname;
    public String unameLast;
    public String path;
    public String packge;
    private String listePage;
    public String pageIndex;

    public Module(String pageIndex) {
        uname = this.getClass().getSimpleName();
        packge = StringUtils.substringAfter(this.getClass().getPackageName(), "modules.");
        this.path = "modules/" + packge.replace('.', '/');
        this.unameLast = uname.substring(6);
        this.listePage = "ListePage" + unameLast;
        this.pageIndex = pageIndex;
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
        Contexte.getInstance().addPage(page);
        return page;
    }

}
