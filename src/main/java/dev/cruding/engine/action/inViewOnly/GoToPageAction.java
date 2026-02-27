package dev.cruding.engine.action.inViewOnly;

import org.apache.commons.lang3.StringUtils;
import dev.cruding.engine.action.Action;
import dev.cruding.engine.action.inViewOnly.injection.GoToPageViewInjection;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.gen.Context;

public class GoToPageAction extends Action {

    public GoToPageAction(Entity entity, Element element, String targetPageName) {
        super(ActionType.NOUI, "goTo" + targetPageName, entity, element);
        this.targetPage = Context.getInstance().getPage(targetPageName);
        inViewOnly();
    }

    public Action lcoreName(String lcoreName) {
        this.lcoreName = lcoreName;
        this.ucoreName = StringUtils.capitalize(lcoreName);

        this.lnameWithoutEntity(this.lcoreName);
        this.lnameWithEntity(this.lcoreName);
        return this;
    }


    public void overrideActionInjection() {
        viewActionInjection = new GoToPageViewInjection(targetPage);
    }
}
