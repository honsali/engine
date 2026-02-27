package dev.cruding.engine.gen;

import org.apache.commons.lang3.StringUtils;

public class Module {

    public String id;
    public String uname;
    public String unameLast;
    public String lnameLast;
    public String path;
    public String packge;
    public String pageList;
    public String pageIndex;
    public boolean isParent;
    public boolean isTabMenu;
    public String icon;
    public int lastPosition = 0;

    public Module() {
        uname = this.getClass().getSimpleName();
        packge = StringUtils.substringAfter(this.getClass().getPackageName(), "modules.");
        this.path = "modules/" + packge.replace('.', '/');
        this.unameLast = uname.substring(6);
        this.lnameLast = StringUtils.uncapitalize(this.unameLast);
        this.pageList = "ListePage" + unameLast;
    }

    public String pageList(String path_, boolean inElement) {
        String rp = Util.getRelativePath(path_, path, inElement);
        return rp + "/" + pageList;
    }

    public String pageList(String path_) {
        return pageList(path_, false);
    }

    public Page addPage(String name, ElementComposer elementComposer) {
        Page page = new Page(this, name, elementComposer);
        page.position = lastPosition++;
        Context.getInstance().addPage(page);
        return page;
    }

    public Module menuIcon(String icon) {
        this.icon = icon;
        return this;
    }
}
