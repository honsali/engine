package dev.cruding.engine.action.inViewOnly;

import java.util.Objects;
import org.apache.commons.lang3.StringUtils;
import dev.cruding.engine.action.Action;
import dev.cruding.engine.action.inViewOnly.injection.GoToPageViewInjection;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.gen.Page;

public class GoToPageAction extends Action {

    public GoToPageAction(Entity entity, Element element, Page targetPage) {
        super(ActionType.NOUI, actionName(targetPage), entity, element);
        this.targetPage = Objects.requireNonNull(targetPage, "Target page cannot be null");
        inViewOnly();
    }

    private static String actionName(Page targetPage) {
        return "goTo" + Objects.requireNonNull(targetPage, "Target page cannot be null").name;
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
