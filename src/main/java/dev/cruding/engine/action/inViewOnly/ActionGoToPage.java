package dev.cruding.engine.action.inViewOnly;

import org.apache.commons.lang3.StringUtils;
import dev.cruding.engine.action.Action;
import dev.cruding.engine.action.inViewOnly.injection.ViewGoToPageInjection;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entite.Entite;
import dev.cruding.engine.gen.Contexte;

public class ActionGoToPage extends Action {

    public ActionGoToPage(Entite entite, Element element, String targetPageName) {
        super(ActionType.NOUI, "goTo" + targetPageName, entite, element);
        this.targetPage = Contexte.getInstance().getPage(targetPageName);
        inViewOnly();
    }

    public Action lcoreName(String lcoreName) {
        this.lcoreName = lcoreName;
        this.ucoreName = StringUtils.capitalize(lcoreName);

        this.lnameSansEntite(this.lcoreName);
        this.lnameAvecEntite(this.lcoreName);
        return this;
    }


    public void overrideActionInjection() {
        viewActionInjection = new ViewGoToPageInjection(targetPage);
    }
}
