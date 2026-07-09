package dev.cruding.engine.gen;

import org.apache.commons.lang3.StringUtils;
import dev.cruding.engine.entity.Entity;

public class Module {

    public String id;
    public String uname;
    public String unameLast;
    public String lnameLast;
    public String path;
    public String packge;
    public String pageList;
    public Page pageIndex;
    public boolean isParent;
    public boolean isTabMenu;
    public String icon;
    public int lastPosition = 0;

    public Module(String uname, String packge) {
        if (StringUtils.isBlank(uname)) {
            throw new ContextException("Module name cannot be null or empty");
        }
        if (!uname.startsWith("Module")) {
            throw new ContextException("Module name must start with 'Module': " + uname);
        }
        if (StringUtils.isBlank(packge)) {
            throw new ContextException("Module package cannot be null or empty");
        }
        this.uname = uname;
        this.packge = packge;
        this.path = "modules/" + packge.replace('.', '/');
        this.unameLast = uname.substring(6);
        this.lnameLast = StringUtils.uncapitalize(this.unameLast);
        this.pageList = "ListePage" + unameLast;
        Context.getInstance().addModule(this);
    }

    public String pageList(String path_, boolean inElement) {
        String rp = Util.getRelativePath(path_, path, inElement);
        return rp + "/" + pageList;
    }

    public String pageList(String path_) {
        return pageList(path_, false);
    }

    public Page addPage(Class<? extends Entity> entityType, ElementComposer elementComposer) {
        Page page = new Page(this, entityType, elementComposer);
        page.position = lastPosition++;
        Context.getInstance().addPage(page);
        return page;
    }

    public Module menuIcon(String icon) {
        this.icon = icon;
        return this;
    }

    public Module parent() {
        this.isParent = true;
        return this;
    }

    public Module tabMenu() {
        this.isTabMenu = true;
        return this;
    }
}
